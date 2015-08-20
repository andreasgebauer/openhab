package org.openhab.binding.cec.internal.protocol;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("data")
public class DataMapping {

	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private Integer length;
	@XStreamAsAttribute
	private String type;
	@XStreamAsAttribute
	private String target;

	public String getName() {
		return name;
	}

	public Integer getLength() {
		return length;
	}

	public String getType() {
		return type;
	}

	public String getTarget() {
		return target;
	}
}
