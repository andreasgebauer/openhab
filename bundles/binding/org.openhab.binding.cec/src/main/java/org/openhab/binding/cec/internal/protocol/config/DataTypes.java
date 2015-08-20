package org.openhab.binding.cec.internal.protocol.config;

import java.util.List;

import org.openhab.binding.cec.internal.protocol.datatypes.def.DataTypeDefinition;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class DataTypes {

	@XStreamImplicit
	private List<DataTypeDefinition> dataType;

	public DataTypeDefinition getByName(String name) {
		for (DataTypeDefinition dataTypeDefinition : dataType) {
			if (dataTypeDefinition.getName().equals(name)) {
				return dataTypeDefinition;
			}
		}
		return null;
	}
}
