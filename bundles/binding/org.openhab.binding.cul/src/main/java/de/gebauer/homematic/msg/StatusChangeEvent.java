package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.DeviceState;
import de.gebauer.homematic.device.AbstractDevice;

public class StatusChangeEvent extends AbstractEvent implements Message {

    public static class ChannelStatus implements DeviceState {

	public short channel;
	public String peerId;
	public short peerChannel;

	@Override
	public String toString() {
	    return "ChannelStatus [channel=" + channel + ", peerId=" + peerId + ":" + peerChannel + "]";
	}
    }

    private DeviceState deviceStateData;
    private short subtype;

    public StatusChangeEvent(RawMessage msg, AbstractDevice device, AbstractDevice dstDevice, DeviceState chStatus, short channel) {
	super(msg, device, dstDevice, channel);
	this.deviceStateData = chStatus;
	this.subtype = 0x06;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "StatusChangeEvent [msg=" + getRawMessage() + "chStatus=" + deviceStateData + "]";
    }
}
