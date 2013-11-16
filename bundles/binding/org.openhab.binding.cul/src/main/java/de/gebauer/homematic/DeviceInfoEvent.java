package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public class DeviceInfoEvent extends AbstractEvent {

    private DeviceInfo deviceInfo;

    public DeviceInfoEvent(RawMessage msg, Device srcDevice) {
	super(msg, srcDevice, null);
	this.deviceInfo = srcDevice.getInfo();
    }

    public EventType getType() {
	return EventType.DEVICE_INFO;
    }

    public DeviceInfo getInfo() {
	return this.deviceInfo;
    }

    @Override
    public String toString() {
	return "DeviceInfoEvent [deviceInfo=" + this.deviceInfo + "]";
    }

}
