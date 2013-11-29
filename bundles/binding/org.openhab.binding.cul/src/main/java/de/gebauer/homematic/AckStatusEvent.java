package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public class AckStatusEvent extends AbstractEvent {

    private final int channel;
    private final int status;
    private final int rssi;
    private DeviceData deviceData;
    private Boolean success;

    public AckStatusEvent(RawMessage msg, Device device, Device receiver, int channel, int status, int rssi) {
	super(msg, device, receiver);
	this.channel = channel;
	this.status = status;
	this.rssi = rssi;
    }

    public AckStatusEvent(RawMessage msg, Device sender, Device receiver, int chnl, DeviceData deviceData,
	    boolean success) {
	this(msg, sender, receiver, chnl, deviceData);
	this.success = success;
    }

    public AckStatusEvent(RawMessage msg, Device sender, Device receiver, int chnl, DeviceData deviceData) {
	this(msg, sender, receiver, chnl, -1, -1);
	this.deviceData = deviceData;
    }

    public EventType getType() {
	return EventType.ACK;
    }

    public Boolean getSuccess() {
	return success;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	if (deviceData != null) {
	    sb.append("AckStatus [channel=" + channel + ", deviceData=" + deviceData);
	} else {
	    sb.append("AckStatus [channel=" + channel + ", status=" + status + ", rssi=" + rssi + ", raw=" + msg);
	}
	if (this.success != null) {
	    sb.append(", success=" + this.success);
	}
	sb.append("]");
	return sb.toString();
    }

    public DeviceData getData() {
	return deviceData;
    }

}
