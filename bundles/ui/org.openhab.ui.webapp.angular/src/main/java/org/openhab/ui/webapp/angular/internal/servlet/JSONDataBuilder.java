package org.openhab.ui.webapp.angular.internal.servlet;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.openhab.core.library.types.DateTimeType;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.HSBType;
import org.openhab.core.types.State;

public class JSONDataBuilder {

	private JsonObjectBuilder objBuilder;

	public JSONDataBuilder(JsonObjectBuilder objBuilder) {
		this.objBuilder = objBuilder;
	}

	static JSONDataBuilder createStateMessage(String name, String label, String icon) {
		final JsonObjectBuilder objBuilder = Json.createObjectBuilder();
		objBuilder.add("id", name);
		if (label != null) {
			objBuilder.add("label", label);
		}
		if (icon != null) {
			objBuilder.add("icon", icon);
		}
		return new JSONDataBuilder(objBuilder);
	}

	JSONDataBuilder addValue(Date timestamp, State state) {
		if (timestamp != null) {
			objBuilder.add("timestamp", timestamp.getTime());
		}
		if (state instanceof HSBType) {
			float hue = ((HSBType) state).getHue().floatValue();
			float sat = ((HSBType) state).getSaturation().floatValue();
			float bri = ((HSBType) state).getBrightness().floatValue();
			objBuilder.add("value", hue + "," + sat + "," + bri);
			objBuilder.add("valueType", "hsb");
		} else if (state instanceof DecimalType) {
			objBuilder.add("value", ((DecimalType) state).toBigDecimal());
		} else if (state instanceof DateTimeType) {
			Calendar calendar = ((DateTimeType) state).getCalendar();
			objBuilder.add("value", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(calendar.getTime()));
			objBuilder.add("valueType", "datetime");
		} else {
			objBuilder.add("value", state.toString());
		}
		return this;
	}

	public JsonObject build() {
		return objBuilder.build();
	}

	public void add(String key, String value) {
		this.objBuilder.add(key, value);
	}

	public void add(String string, JsonValue value) {
		this.objBuilder.add(string, value);
	}

	public void add(String key, JsonArrayBuilder value) {
		this.objBuilder.add(key, value);
	}

	public void add(String key, Long value) {
		this.objBuilder.add(key, value);
	}

	public void add(String key, BigDecimal value) {
		this.objBuilder.add(key, value);
	}

	public JsonObjectBuilder builder() {
		return this.objBuilder;
	}
}
