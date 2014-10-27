package de.gebauer.homematic.hmlcsw1pbufm;

import de.gebauer.homematic.msg.AbstractMessage;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.MessageType;

public class SwitchStateMessage extends AbstractMessage {

    private boolean state;

    public SwitchStateMessage(AbstractMessageParameter parameterObject, boolean state) {
	super(parameterObject);
	this.state = state;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    @Override
    public String getPayload() {
	return super.getRawMessage().getPayload();
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    public boolean isOn() {
	return state;
    }

    @Override
    public String toString() {
	return "SwitchStateMessage [state=" + state + ", msg=" + getRawMessage() + "]";
    }


}
