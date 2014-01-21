package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.StatusChangeEvent.ChannelStatus;

/**
 * Send this message to Request the actuator status.
 * 
 * @author andi
 * 
 */
public class InfoActuatorStatusMessage extends AbstractMessage implements Message {

    protected ChannelStatus chStatus;

    public InfoActuatorStatusMessage(RawMessage msg, AbstractDevice src, AbstractDevice dst, short channel, ChannelStatus chStatus) {
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
