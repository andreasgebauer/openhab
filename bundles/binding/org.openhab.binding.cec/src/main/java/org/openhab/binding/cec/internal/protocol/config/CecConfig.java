package org.openhab.binding.cec.internal.protocol.config;

import java.util.ArrayList;
import java.util.List;

import org.openhab.binding.cec.internal.protocol.datatypes.def.MessageType;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class CecConfig {

	@XStreamImplicit
	private List<MessageType> messages = new ArrayList<MessageType>();

	public List<MessageType> getMessages() {
		return messages;
	}

	@Override
	public String toString() {
		return "CecConfig [messages=" + messages + "]";
	}

}
