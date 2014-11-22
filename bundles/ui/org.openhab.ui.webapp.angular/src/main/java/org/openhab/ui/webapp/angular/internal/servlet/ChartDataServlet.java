package org.openhab.ui.webapp.angular.internal.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.openhab.core.persistence.FilterCriteria;
import org.openhab.core.persistence.FilterCriteria.Ordering;
import org.openhab.core.persistence.HistoricItem;
import org.openhab.core.persistence.PersistenceService;
import org.openhab.core.persistence.QueryablePersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChartDataServlet extends BaseServlet {

    private static final Logger logger = LoggerFactory.getLogger(ChartDataServlet.class);

    protected Map<String, QueryablePersistenceService> persistenceServices = new HashMap<String, QueryablePersistenceService>();

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

    private void processRequest(InputStream input, ServletOutputStream output) {
	QueryablePersistenceService queryablePersistenceService = persistenceServices.get("rrd4j");
	if (queryablePersistenceService == null) {
	    return;
	}

	JsonReader reader = Json.createReader(input);
	JsonObject readObject = reader.readObject();

	logger.debug("Got request: " + readObject);

	JsonArrayBuilder writeArray = Json.createArrayBuilder();

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

			while (it.hasNext()) {
			    HistoricItem historic = it.next();
			    valuesBuilder.add(DataServlet.addValue(historic.getTimestamp(), historic.getState(), Json.createObjectBuilder()));
			}

			JsonArray values = valuesBuilder.build();
			if (values.isEmpty()) {
			    return;
			}

			final JsonObjectBuilder createStateMessage = Json.createObjectBuilder();
			createStateMessage.add("id", itemName);
			createStateMessage.add("values", values);
			createStateMessage.add("begin", begin.getTimeInMillis());
			createStateMessage.add("end", end.getTime());

			writeArray.add(createStateMessage);
		    }
		}
	    }
	}

	Json.createWriter(output).writeArray(writeArray.build());
    }

    @Override
    public String getServletInfo() {
	return "ChartData Servlet";
    }

    @Override
    public void destroy() {
    }

    @Override
    protected String getServletName() {
	return "chartdata";
    }

}
