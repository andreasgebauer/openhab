package org.openhab.ui.webapp.angular.internal.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;
import org.eclipse.jetty.websocket.WebSocketServlet;
import org.openhab.core.items.GenericItem;
import org.openhab.core.items.Item;
import org.openhab.core.items.ItemRegistry;
import org.openhab.core.persistence.FilterCriteria;
import org.openhab.core.persistence.FilterCriteria.Ordering;
import org.openhab.core.persistence.HistoricItem;
import org.openhab.core.persistence.PersistenceService;
import org.openhab.core.persistence.QueryablePersistenceService;
import org.openhab.core.types.State;
import org.openhab.io.net.http.SecureHttpContext;
import org.openhab.model.sitemap.Widget;
import org.openhab.model.sitemap.impl.WidgetImpl;
import org.openhab.ui.items.ItemUIRegistry;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChartDataServlet extends WebSocketServlet {

    private final class WebSocketImpl implements OnTextMessage {
	private Connection connection;
	private StateChangeListener listener;
	private Map<String, QueryablePersistenceService> persistenceServices;
	private HttpServletRequest initiatingRequest;

	public WebSocketImpl(HttpServletRequest req, Map<String, QueryablePersistenceService> persistenceServices) {
	    this.initiatingRequest = req;
	    this.persistenceServices = persistenceServices;
	}

	@Override
	public void onOpen(final Connection connection) {
	    logger.debug("Client {}:{} connected", this.initiatingRequest.getRemoteAddr(), this.initiatingRequest.getRemotePort());

	    this.connection = connection;
	    // this.connection.setMaxIdleTime(10000);
	}

	@Override
	public void onClose(final int closeCode, final String message) {
	    logger.debug("Client {}:{} disconnected", this.initiatingRequest.getRemoteAddr(), this.initiatingRequest.getRemotePort());

	    close();
	}

	private void close() {
	    for (final Item item : ChartDataServlet.this.itemRegistry.getItems()) {
		if (item instanceof GenericItem) {
		    ((GenericItem) item).removeStateChangeListener(this.getListener());
		}
	    }

	    this.connection.close();
	}

	@Override
	public void onMessage(String data) {
	    logger.debug("Data queried: " + data);

	    QueryablePersistenceService queryablePersistenceService = persistenceServices.get("rrd4j");
	    if (queryablePersistenceService == null) {
		return;
	    }

	    JsonReader reader = Json.createReader(new StringReader(data));
	    JsonObject readObject = reader.readObject();
	    /**
	     * Comes in the form of { Living: [{begin:640000,end:1433212122}], Sleeping: [320000] } where [begin, end]
	     */
	    for (String itemName : readObject.keySet()) {
		JsonValue periodVal = readObject.get(itemName);
		if (periodVal.getValueType() == ValueType.ARRAY) {

		    for (JsonValue jsonValue : ((JsonArray) periodVal)) {
			if (jsonValue.getValueType() == ValueType.OBJECT) {
			    long beginMillis = ((JsonObject) jsonValue).getJsonNumber("begin").longValue();
			    long endMillis = ((JsonObject) jsonValue).getJsonNumber("end").longValue();

			    FilterCriteria filter = new FilterCriteria();

			    Date end = new Date();
			    end.setTime(endMillis);
			    Calendar begin = Calendar.getInstance();
			    begin.setTimeInMillis(beginMillis);

			    filter.setBeginDate(begin.getTime());
			    filter.setEndDate(end);
			    filter.setItemName(itemName);
			    filter.setOrdering(Ordering.ASCENDING);

			    Iterable<HistoricItem> query = queryablePersistenceService.query(filter);
			    Iterator<HistoricItem> it = query.iterator();

			    JsonArrayBuilder valuesBuilder = Json.createArrayBuilder();

			    try {
				while (it.hasNext() && connection.isOpen()) {
				    HistoricItem historic = it.next();
				    addValue(historic.getTimestamp(), historic.getState(), valuesBuilder);
				}

				JsonArray values = valuesBuilder.build();
				if (values.isEmpty()) {
				    return;
				}

				String label = ChartDataServlet.this.itemUIRegistry.getLabel(fakeWidget(itemName));
				String icon = ChartDataServlet.this.itemUIRegistry.getIcon(fakeWidget(itemName));

				JsonObjectBuilder createStateMessage = createStateMessage(itemName, label, icon);
				createStateMessage.add("values", values);
				createStateMessage.add("begin", begin.getTimeInMillis());
				createStateMessage.add("end", end.getTime());

				this.sendMessage(createStateMessage.build());
			    } catch (IOException e) {
				logger.error("Error during send state.", e);
			    }
			}
		    }

		}
	    }
	}

	public void sendMessage(JsonObject obj) throws IOException {
	    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    final JsonWriter wr = Json.createWriter(baos);
	    wr.writeObject(obj);
	    this.connection.sendMessage(baos.toString());
	}

	public StateChangeListener getListener() {
	    return listener;
	}

	public void setListener(StateChangeListener listener) {
	    this.listener = listener;
	}
    }

    private final class StateChangeListener implements org.openhab.core.items.StateChangeListener {

	private WebSocketImpl socket;

	StateChangeListener(WebSocketImpl socket) {
	    this.socket = socket;
	}

	@Override
	public void stateUpdated(final Item item, final State state) {
	    // logger.debug("State of {} updated to {}", item, state);

	    // sendMessage(item);
	}

	@Override
	public void stateChanged(final Item item, final State oldState, final State newState) {
	    // logger.debug("State of {} changed from {} to {}", item.getName(), oldState, newState);
	    final String name = item.getName();
	    String label = ChartDataServlet.this.itemUIRegistry.getLabel(fakeWidget(name));
	    String icon = ChartDataServlet.this.itemUIRegistry.getIcon(fakeWidget(name));
	    State state = item.getState();
	    sendStateMessage(name, label, icon, state);
	}

	private void sendStateMessage(String name, String label, String icon, State state) {
	    logger.debug("Sending name:{} label:{} icon:{} state:{}", new String[] { name, label, icon, state.toString() });
	    final JsonObjectBuilder msg = createStateMessage(name, label, icon);
	    msg.add("values", addValue(new Date(), state, Json.createArrayBuilder()));

	    try {
		socket.sendMessage(msg.build());
	    } catch (final IOException e) {
		logger.error("Unable to send. Closing connection", e);
		this.socket.close();

	    }
	}

    }

    /**
     *
     */
    private static final long serialVersionUID = -6844746825123187670L;
    private static final Logger logger = LoggerFactory.getLogger(ChartDataServlet.class);

    private HttpService httpService;
    private ItemRegistry itemRegistry;
    private ItemUIRegistry itemUIRegistry;
    protected Map<String, QueryablePersistenceService> persistenceServices = new HashMap<String, QueryablePersistenceService>();

    private final List<WebSocketImpl> sockets = new ArrayList<WebSocketImpl>();

    public void setHttpService(final HttpService httpService) {
	this.httpService = httpService;
    }

    public void unsetHttpService(final HttpService httpService) {
	this.httpService = null;
    }

    public void setItemRegistry(final ItemRegistry itemRegistry) {
	this.itemRegistry = itemRegistry;
    }

    public void unsetItemRegistry(final ItemRegistry itemRegistry) {
	this.itemRegistry = null;
    }

    public void setItemUIRegistry(final ItemUIRegistry itemUIRegistry) {
	this.itemUIRegistry = itemUIRegistry;
    }

    public void unsetItemUIRegistry(final ItemUIRegistry itemUIRegistry) {
	this.itemUIRegistry = null;
    }

    public void addPersistenceService(PersistenceService service) {
	if (service instanceof QueryablePersistenceService)
	    persistenceServices.put(service.getName(), (QueryablePersistenceService) service);
    }

    public void removePersistenceService(PersistenceService service) {
	persistenceServices.remove(service.getName());
    }

    protected void activate() {
	try {
	    final String mapping = BaseServlet.WEBAPP_ALIAS + "/" + "websocket";
	    logger.debug("Starting up " + this.getClass().getSimpleName() + " at " + mapping);

	    final Hashtable<String, String> props = new Hashtable<String, String>();
	    this.httpService.registerServlet(mapping, this, props, this.createHttpContext());

	} catch (final NamespaceException e) {
	    logger.error("Error during servlet startup", e);
	} catch (final ServletException e) {
	    logger.error("Error during servlet startup", e);
	}
    }

    protected void deactivate() {
	for (WebSocketImpl socket : this.sockets) {
	    try {
		socket.close();
	    } catch (Exception e) {
	    }
	}

	this.httpService.unregister(BaseServlet.WEBAPP_ALIAS + "/" + "websocket");

    }

    private HttpContext createHttpContext() {
	final HttpContext defaultHttpContext = this.httpService.createDefaultHttpContext();
	return new SecureHttpContext(defaultHttpContext, "openHAB.org");
    }

    @Override
    public WebSocket doWebSocketConnect(final HttpServletRequest arg0, final String arg1) {
	WebSocketImpl socket = new WebSocketImpl(arg0, this.persistenceServices);
	StateChangeListener listener = new StateChangeListener(socket);
	socket.setListener(listener);
	for (final Item item : this.itemRegistry.getItems()) {
	    if (item instanceof GenericItem) {
		((GenericItem) item).addStateChangeListener(listener);
	    }
	}

	return socket;
    }

    private Widget fakeWidget(final String name) {
	Widget w = new WidgetImpl() {
	    @Override
	    public String getItem() {
		return name;
	    }
	};
	return w;
    }

    private static JsonObjectBuilder createStateMessage(String name, String label, String icon) {
	final JsonObjectBuilder objBuilder = Json.createObjectBuilder();
	objBuilder.add("id", name);
	if (label != null) {
	    objBuilder.add("label", label);
	}
	if (icon != null) {
	    objBuilder.add("icon", icon);
	}
	return objBuilder;
    }

    private static JsonArrayBuilder addValue(Date timestamp, State state, JsonArrayBuilder valuesBuilder) {
	JsonObjectBuilder valueBuilder = Json.createObjectBuilder();
	if (timestamp != null) {
	    valueBuilder.add("timestamp", timestamp.getTime());
	}
	valueBuilder.add("value", state.toString());
	valuesBuilder.add(valueBuilder.build());
	return valuesBuilder;
    }

}
