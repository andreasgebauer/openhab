package org.openhab.ui.webapp.angular.internal.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;
import org.eclipse.jetty.websocket.WebSocketServlet;
import org.openhab.core.items.GenericItem;
import org.openhab.core.items.Item;
import org.openhab.core.items.ItemRegistry;
import org.openhab.core.types.State;
import org.openhab.io.net.http.SecureHttpContext;
import org.openhab.ui.webapp.angular.internal.servlet.DataServlet.UpdateMode;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataServlet extends WebSocketServlet {

	public enum UpdateMode {
		UPDATED, CHANGED
	}

	private final class WebSocketImpl implements OnTextMessage {
		private Connection connection;
		private StateChangeListener listener;
		private HttpServletRequest initiatingRequest;

		public WebSocketImpl(HttpServletRequest req) {
			this.initiatingRequest = req;
		}

		@Override
		public void onOpen(final Connection connection) {
			logger.debug("Client {}:{} connected", this.initiatingRequest.getRemoteAddr(),
					this.initiatingRequest.getRemotePort());

			this.connection = connection;
			// this.connection.setMaxIdleTime(10000);
		}

		@Override
		public void onClose(final int closeCode, final String message) {
			logger.debug("Client {}:{} disconnected", this.initiatingRequest.getRemoteAddr(),
					this.initiatingRequest.getRemotePort());

			close();
		}

		private void close() {
			for (final Item item : DataServlet.this.itemRegistry.getItems()) {
				if (item instanceof GenericItem) {
					((GenericItem) item).removeStateChangeListener(this.getListener());
				}
			}

			this.connection.close();
		}

		@Override
		public void onMessage(String data) {
			logger.debug("Data queried: " + data);

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
		private UpdateMode updateMode;

		StateChangeListener(WebSocketImpl socket, UpdateMode updateMode) {
			this.socket = socket;
			this.updateMode = updateMode;
		}

		@Override
		public void stateUpdated(final Item item, final State state) {
			// logger.debug("State of {} updated to {}", item, state);
			if (this.updateMode == UpdateMode.UPDATED) {
				sendStateMessage(item.getName(), null, null, state);
			}
		}

		@Override
		public void stateChanged(final Item item, final State oldState, final State newState) {
			// logger.debug("State of {} changed from {} to {}", item.getName(), oldState, newState);
			if (this.updateMode == UpdateMode.CHANGED) {
				sendStateMessage(item.getName(), null, null, newState);
			}
		}

		private void sendStateMessage(String name, String label, String icon, State state) {
			logger.debug("Sending name:{} label:{} icon:{} state:{}", new Object[] { name, label, icon, state });
			final JSONDataBuilder msg = JSONDataBuilder.createStateMessage(name, label, icon);
			msg.addValue(new Date(), state);

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
	private static final Logger logger = LoggerFactory.getLogger(DataServlet.class);

	private HttpService httpService;
	private ItemRegistry itemRegistry;

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
	public WebSocket doWebSocketConnect(final HttpServletRequest req, final String arg1) {
		WebSocketImpl socket = new WebSocketImpl(req);
		UpdateMode mode = UpdateMode.UPDATED;
		if (req.getHeader("UpdateMode") != null) {
			mode = UpdateMode.valueOf(req.getHeader("UpdateMode").toUpperCase());
		}
		StateChangeListener listener = new StateChangeListener(socket, mode);
		socket.setListener(listener);
		for (final Item item : this.itemRegistry.getItems()) {
			if (item instanceof GenericItem) {
				((GenericItem) item).addStateChangeListener(listener);
			}
		}

		return socket;
	}

}
