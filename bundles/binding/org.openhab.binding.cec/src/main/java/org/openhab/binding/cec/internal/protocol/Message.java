package org.openhab.binding.cec.internal.protocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openhab.binding.cec.internal.protocol.datatypes.def.MessageType;

public class Message {

	private MessageType type;
	private List<Payload> data;

	public Message(MessageType type) {
		this.type = type;
		this.data = new ArrayList<Payload>();
	}

	public Payload getPayload() {
		if (data.size() > 1) {
			throw new IllegalStateException("Multiple payloads read. Need to specify which by using method getPayload(String)");
		}
		return data.get(0);
	}

	public Payload getPayload(String string) {
		for (Payload payload : data) {
			if (payload.getName().equals(string)) {
				return payload;
			}
		}
		return null;
	}

	public void addData(Payload payload) {
		this.data.add(payload);
	}

	public boolean hasOpcode(int... i) {
		for (int j : i) {
			if (this.type.getId() == j)
				return true;
		}
		return false;
	}

	public MessageType getType() {
		return this.type;
	}

	public List<Payload> getPayloads() {
		return Collections.unmodifiableList(this.data);
	}

	@Override
	public String toString() {
		String message = type.toString();
		if (!data.isEmpty()) {
			message += ": " + data;
		}
		return message;
	}

}