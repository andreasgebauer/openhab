package org.openhab.binding.cec.internal.protocol.datatypes.def;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("dataType")
public class DataTypeDefinition {

	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private Integer length;
	@XStreamAsAttribute
	private Class<?> type;

	public String getName() {
		return name;
	}

	public Integer getLength() {
		return length;
	}

	public Class<?> getType() {
		return type;
	}

}
