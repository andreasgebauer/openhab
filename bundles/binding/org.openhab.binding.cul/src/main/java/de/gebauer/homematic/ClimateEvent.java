package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public class ClimateEvent extends AbstractEvent {

    private static final byte COMMAND = 0x58;

    private final int command;
    private final int valvePos;

    public ClimateEvent(RawMessage msg, Device srcDevice, Device receiver, int cmd, int vp) {
	super(msg, srcDevice, receiver);
	command = cmd;
	valvePos = vp;
    }

    public EventType getType() {
	return EventType.CLIMATE;
    }

    @Override
    public String toString() {
	return "ClimateEvent [command=" + command + ", valvePos=" + valvePos + ", raw=" + super.msg + "]";
    }

}
