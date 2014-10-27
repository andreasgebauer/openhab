package de.gebauer.homematic.hmlcsw1pbufm;

import de.gebauer.homematic.hmlcsw1pbufm.HMLCSW1PBUFMInterpreter.SwitchState;
import de.gebauer.homematic.msg.AbstractMessage;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.MessageType;

public class SwitchStateMessage extends AbstractMessage {

    private SwitchState state;

    public SwitchStateMessage(AbstractMessageParameter msgParam, SwitchState state) {
	super(msgParam);
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

    public SwitchState getState() {
	return state;
    }

    @Override
    public String toString() {
	return "SwitchStateMessage [state=" + state + ", msg=" + getRawMessage() + "]";
    }

}
