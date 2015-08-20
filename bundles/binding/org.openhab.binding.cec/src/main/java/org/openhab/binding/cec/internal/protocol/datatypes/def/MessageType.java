package org.openhab.binding.cec.internal.protocol.datatypes.def;

import java.util.Collections;
import java.util.List;

import org.openhab.binding.cec.internal.protocol.DataMapping;
import org.openhab.binding.cec.internal.protocol.converter.HexToIntegerConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("message")
public class MessageType {
	@XStreamConverter(HexToIntegerConverter.class)
	@XStreamAsAttribute
	private Integer id;
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String category;
	@XStreamAsAttribute
	private String target;
	private String description;

	private List<DataMapping> datas;

	public MessageType(Integer id, String category, String description, List<DataMapping> data) {
		super();
		this.id = id;
		this.category = category;
		this.description = description;
		this.datas = data;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getTarget() {
		return target;
	}

	public List<DataMapping> getData() {
		if (datas != null) {
			return datas;
		}
		return Collections.emptyList();
	}

	@Override
	public String toString() {
		return name;
	}

}
