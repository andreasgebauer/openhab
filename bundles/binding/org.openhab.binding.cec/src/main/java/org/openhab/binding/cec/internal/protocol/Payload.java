package org.openhab.binding.cec.internal.protocol;

import org.openhab.binding.cec.internal.protocol.datatypes.def.DataTypeDefinition;

public class Payload {

	private Object value;
	private DataTypeDefinition dataType;
	private DataMapping dataMapping;

	public Payload(DataMapping dataMapping, Object value, DataTypeDefinition dataType) {
		super();
		this.dataMapping = dataMapping;
		this.value = value;
		this.dataType = dataType;
	}

	public String getName() {
		return this.dataMapping.getName();
	}

	public Object getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.dataMapping.getName() + ": " + value + "->" + this.dataMapping.getTarget();
	}

	public String getTarget() {
		return dataMapping.getTarget();
	}

}
