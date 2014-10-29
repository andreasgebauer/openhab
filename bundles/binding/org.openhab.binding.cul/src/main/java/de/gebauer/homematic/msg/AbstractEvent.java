package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public abstract class AbstractEvent extends AbstractMessage {

    public AbstractEvent(RawMessage msg, AbstractDevice src, AbstractDevice dst, short channel) {
	this(new AbstractMessageParameter(msg, src, dst, channel, null));
    }

    public AbstractEvent(AbstractMessageParameter abstractMessageParameter) {
	super(abstractMessageParameter);
    }

    @Override
    public String getPayload() {
	// we don't need a payload
	return null;
    }
}
