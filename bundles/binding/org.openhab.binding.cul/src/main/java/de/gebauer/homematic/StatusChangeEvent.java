package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public class StatusChangeEvent extends AbstractEvent implements Event {

    public static class ChannelStatus {

	public int channel;
	public String peerId;

	@Override
	public String toString() {
	    return "ChannelStatus [channel=" + channel + ", peerId=" + peerId + "]";
	}

    }

    private ChannelStatus chStatus;

    public StatusChangeEvent(RawMessage msg, Device device, Device dstDevice, ChannelStatus chStatus) {
	super(msg, device, dstDevice);
	this.chStatus = chStatus;
    }

    @Override
    public EventType getType() {
	return EventType.STATUS_CHANGE;
    }

    @Override
    public String toString() {
	return "StatusChangeEvent [msg=" + msg + "chStatus=" + chStatus + "]";
    }

}
