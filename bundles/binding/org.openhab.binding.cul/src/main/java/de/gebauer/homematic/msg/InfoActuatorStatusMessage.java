package de.gebauer.homematic.msg;

import java.math.BigDecimal;

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

    @Deprecated
    public InfoActuatorStatusMessage(RawMessage msg, AbstractDevice src, AbstractDevice dst, ChannelStatus chStatus, BigDecimal rssi) {
	super(new AbstractMessageParameter(msg, src, dst, chStatus.channel));
	this.chStatus = chStatus;
    }

    public InfoActuatorStatusMessage(AbstractMessageParameter param) {
	super(param);
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

    public ChannelStatus getChannelStatus() {
	return chStatus;
    }

}
