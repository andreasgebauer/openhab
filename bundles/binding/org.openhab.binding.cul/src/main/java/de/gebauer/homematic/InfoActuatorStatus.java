package de.gebauer.homematic;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.StatusChangeEvent.ChannelStatus;
import de.gebauer.homematic.device.AbstractDevice;

public class InfoActuatorStatus extends AbstractMessage implements Message {

    protected ChannelStatus chStatus;

    public InfoActuatorStatus(RawMessage msg, AbstractDevice src, AbstractDevice dst, short channel, ChannelStatus chStatus) {
	super(msg, src, dst, channel);
	this.chStatus = chStatus;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    @Override
    public String getPayload() {
	return null;
    }

    @Override
    public boolean needsAck() {
	return true;
    }

}
