package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.DeviceState;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * 
 * subtype channel status ?? rssi
 * 
 * <pre>
 * (ACK_STATUS CHANNEL:0x02 STATUS:0x0E UP:0x00 DOWN:0x00 LOWBAT:0x00 RSSI:0x36) (,RPTEN) 01 02 0E 00 36
 * </pre>
 * 
 * @author andi
 * 
 */
public class AckStatusEvent extends AbstractEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private DeviceState deviceData;
    private Boolean success;

    public AckStatusEvent(RawMessage msg, AbstractDevice src, AbstractDevice dst, short channel, short rssi) {
	this(msg, src, dst, channel);
    }

    public AckStatusEvent(RawMessage msg, AbstractDevice src, AbstractDevice dst, short chnl, short rssi, DeviceState deviceData) {
	super(new AbstractMessageParameter(msg, src, dst, chnl, rssi));
	this.deviceData = deviceData;
    }

    public AckStatusEvent(RawMessage msg, AbstractDevice src, AbstractDevice dst, short chnl, short rssi, boolean success) {
	this(msg, src, dst, chnl, rssi, null);
	this.success = success;
    }

    public AckStatusEvent(RawMessage msg, AbstractDevice src, AbstractDevice dst, short channel, DeviceState deviceState) {
	this(msg, src, dst, channel);
	this.deviceData = deviceState;
    }

    public AckStatusEvent(RawMessage msg, AbstractDevice src, AbstractDevice dst, short channel) {
	super(msg, src, dst, channel);
    }

    public MessageType getType() {
	return MessageType.ACK;
    }

    @Override
    public String getPayload() {
	return getRawMessage().getPayload();
    }

    public Boolean getSuccess() {
	return success;
    }

    public DeviceState getDeviceState() {
	return deviceData;
    }

    @Override
    public boolean needsAck() {
	return false;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("AckStatus " + super.sendString() + " [ch=" + (int) getChannel());
	if (deviceData != null) {
	    sb.append(", data=" + deviceData);
	}
	if (this.success != null) {
	    sb.append(", success=" + this.success);
	}
	// sb.append(", raw=" + msg);
	sb.append("]");
	return sb.toString();
    }

}
