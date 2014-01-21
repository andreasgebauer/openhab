package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;

public class DeviceInfoEvent extends AbstractEvent {

    private DeviceInfo deviceInfo;
    private String cmd;
    private short pChA;
    private short pChB;
    private boolean needsAck;

    public DeviceInfoEvent(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dst, DeviceInfo info, short pChA, short pChB, String cmd) {
	super(msg, srcDevice, dst, (short) -1);
	this.pChA = pChA;
	this.pChB = pChB;
	this.cmd = cmd;
	this.deviceInfo = info;
	this.needsAck = true;
    }

    public MessageType getType() {
	return MessageType.UNKNOWN;
    }

    public DeviceInfo getInfo() {
	return this.deviceInfo;
    }

    @Override
    public boolean needsAck() {
	return needsAck;
    }

    @Override
    public String getPayload() {
	String deviceInfoStr = String.format("%s%04X%s%s", this.deviceInfo.version, this.deviceInfo.mdl.getId(), this.deviceInfo.serNo, deviceInfo.mdl
		.getDeviceType().getId());
	return String.format("%s%02X%02X%s", deviceInfoStr, this.pChA, this.pChB, this.cmd);
    }

    @Override
    public String toString() {
	return "DeviceInfoEvent [msg=" + msg + ", deviceInfo=" + this.deviceInfo + ", cmd=" + this.cmd + "]";
    }
}
