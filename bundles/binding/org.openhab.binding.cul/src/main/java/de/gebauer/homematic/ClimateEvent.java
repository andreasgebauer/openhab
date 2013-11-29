package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public class ClimateEvent extends AbstractEvent {

    private final int command;
    private final int valvePos;

    public ClimateEvent(RawMessage msg, Device srcDevice, Device receiver, int cmd, int vp) {
	super(msg, srcDevice, receiver);
	command = cmd;
	valvePos = vp;
    }

    public int getCommand() {
	return command;
    }

    public int getValvePos() {
	return valvePos;
    }

    public EventType getType() {
	return EventType.CLIMATE;
    }

    @Override
    public String toString() {
	return "ClimateEvent [command=" + command + ", valvePos=" + valvePos + ", raw=" + super.msg + "]";
    }

}
