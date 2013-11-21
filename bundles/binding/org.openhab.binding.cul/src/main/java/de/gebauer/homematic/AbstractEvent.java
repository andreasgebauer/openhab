package de.gebauer.homematic;

import java.io.Serializable;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public abstract class AbstractEvent implements Event, Serializable {

    protected RawMessage msg;
    private Device srcDevice;
    private Device dstDevice;

    public AbstractEvent(RawMessage msg, Device srcDevice, Device dstDevice) {
	this.msg = msg;
	this.srcDevice = srcDevice;
	this.dstDevice = dstDevice;
    }

    public boolean isBroadCast() {
	return this.msg.dst.equals(BROAD_CAST_ADDRESS);
    }

    public int getCount() {
	return Integer.valueOf(this.msg.msgCount, 16);
    }

    public RawMessage getRawMessage() {
	return this.msg;
    }

    public Device getSender() {
	return srcDevice;
    }

    public Device getReceiver() {
	return this.dstDevice;
    }

    @Override
    public String toString() {
	return "AbstractEvent [hmEvent=" + msg + "]";
    }

}
