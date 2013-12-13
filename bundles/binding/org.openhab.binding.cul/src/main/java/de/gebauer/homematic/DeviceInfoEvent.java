package de.gebauer.homematic;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public class DeviceInfoEvent extends AbstractEvent {

    private DeviceInfo deviceInfo;

    public DeviceInfoEvent(RawMessage msg, AbstractDevice srcDevice) {
	super(msg, srcDevice, null, (short) -1);
	this.deviceInfo = srcDevice.getInfo();
    }

    public MessageType getType() {
	return MessageType.UNKNOWN;
    }

    public DeviceInfo getInfo() {
	return this.deviceInfo;
    }

    @Override
    public String toString() {
	return "DeviceInfoEvent [msg=" + msg + ", deviceInfo=" + this.deviceInfo + "]";
    }

    @Override
    public boolean needsAck() {
	return false;
    }

}
