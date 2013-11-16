package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public class AckStatusEvent extends AbstractEvent {

    private final int channel;
    private final int status;
    private final int rssi;
    private DeviceData deviceData;

    public AckStatusEvent(RawMessage msg, Device sender, Device receiver, int chnl, DeviceData deviceData) {
	super(msg, sender, receiver);
	this.channel = chnl;
	this.deviceData = deviceData;
	this.status = -1;
	this.rssi = -1;
    }

    public AckStatusEvent(RawMessage msg, Device device, Device receiver, int channel, int status, int rssi) {
	super(msg, device, receiver);
	this.channel = channel;
	this.status = status;
	this.rssi = rssi;
    }

    public EventType getType() {
	return EventType.ACK;
    }

    @Override
    public String toString() {
	return "AckStatus [channel=" + channel + ", status=" + status + ", rssi=" + rssi + ", raw=" + msg + "]";
    }

    public DeviceData getData() {
	return deviceData;
    }

}
