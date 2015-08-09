package org.openhab.ui.webapp.angular.internal.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.openhab.core.persistence.FilterCriteria;
import org.openhab.core.persistence.FilterCriteria.Ordering;
import org.openhab.core.persistence.HistoricItem;
import org.openhab.core.persistence.PersistenceService;
import org.openhab.core.persistence.QueryablePersistenceService;
import org.openhab.core.types.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChartDataServlet extends BaseServlet {

	private static final Logger logger = LoggerFactory.getLogger(ChartDataServlet.class);

	protected Map<String, QueryablePersistenceService> persistenceServices = new HashMap<String, QueryablePersistenceService>();

	private ExecutorService executorService;

	public void addPersistenceService(PersistenceService service) {
		if (service instanceof QueryablePersistenceService)
			persistenceServices.put(service.getName(), (QueryablePersistenceService) service);
	}

	public void removePersistenceService(PersistenceService service) {
		persistenceServices.remove(service.getName());
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		this.processRequest(req.getInputStream(), res.getOutputStream());
	}

	private void processRequest(InputStream input, final ServletOutputStream output) {
		QueryablePersistenceService queryablePersistenceService = persistenceServices.get("rrd4j");
		if (queryablePersistenceService == null) {
			return;
		}

		JsonReader reader = Json.createReader(input);
		JsonObject readObject = reader.readObject();

		logger.debug("Got request: " + readObject);

		final JsonArrayBuilder writeArray = Json.createArrayBuilder();

		/**
		 * Comes in the form of { Living: [{begin:640000,end:1433212122}], Sleeping: [320000] } where [begin, end]
		 */

		List<FetchDataParameter> dataToFetch = new ArrayList<FetchDataParameter>();
		for (String itemName : readObject.keySet()) {
			JsonValue periodVal = readObject.get(itemName);
			if (periodVal.getValueType() == ValueType.ARRAY) {

				for (JsonValue jsonValue : ((JsonArray) periodVal)) {
					if (jsonValue.getValueType() == ValueType.OBJECT) {
						long beginMillis = ((JsonObject) jsonValue).getJsonNumber("begin").longValue();
						long endMillis = ((JsonObject) jsonValue).getJsonNumber("end").longValue();

						FilterCriteria filter = new FilterCriteria();

						Calendar end = Calendar.getInstance();
						end.setTimeInMillis(endMillis);
						Calendar begin = Calendar.getInstance();
						begin.setTimeInMillis(beginMillis);

						filter.setBeginDate(begin.getTime());
						filter.setEndDate(end.getTime());
						filter.setItemName(itemName);
						filter.setOrdering(Ordering.ASCENDING);

						final FetchDataParameter parameterObject = new FetchDataParameter(queryablePersistenceService, itemName, filter, end, begin);
						dataToFetch.add(parameterObject);

					}
				}
			}
		}
		
		final Thread requestThread = Thread.currentThread();

		final AtomicInteger callables = new AtomicInteger(dataToFetch.size());

		logger.info("Need to execute {} tasks", dataToFetch.size());
		for (final FetchDataParameter fetchDataParameter : dataToFetch) {
			executorService.submit(new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					JsonObjectBuilder fetchData = fetchData(fetchDataParameter);
					if (fetchData != null)
						writeArray.add(fetchData);

					int decrementAndGet = callables.decrementAndGet();
					logger.info("Need to execute {} more tasks", decrementAndGet);

					if (decrementAndGet == 0) {
						logger.info("Releasing the lock.");
						LockSupport.unpark(requestThread);
					}
					return null;
				}
			});
		}
		LockSupport.park();

		logger.info("No more tasks to execute for this request. Writing response.");
		Json.createWriter(output).writeArray(writeArray.build());

	}

	private JsonObjectBuilder fetchData(FetchDataParameter parameterObject) {
		Iterable<HistoricItem> query = parameterObject.queryablePersistenceService.query(parameterObject.filter);
		Iterator<HistoricItem> it = query.iterator();

		JsonArrayBuilder valuesBuilder = Json.createArrayBuilder();

		while (it.hasNext()) {
			HistoricItem historic = it.next();
			State state = historic.getState();

			JSONDataBuilder jsonDataBuilder = new JSONDataBuilder(Json.createObjectBuilder());

			valuesBuilder.add(jsonDataBuilder.addValue(historic.getTimestamp(), state).build());
		}

		JsonArray values = valuesBuilder.build();
		if (values.isEmpty()) {
			return null;
		}

		final JsonObjectBuilder createStateMessage = Json.createObjectBuilder();
		createStateMessage.add("id", parameterObject.itemName);
		createStateMessage.add("values", values);
		createStateMessage.add("begin", parameterObject.begin.getTimeInMillis());
		createStateMessage.add("end", parameterObject.end.getTimeInMillis());

		return createStateMessage;
	}

	@Override
	public String getServletInfo() {
		return "ChartData Servlet";
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		executorService = Executors.newFixedThreadPool(5);
	}

	@Override
	public void destroy() {
		executorService.shutdown();
	}

	@Override
	protected String getServletName() {
		return "chartdata";
	}

}
