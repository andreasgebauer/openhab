package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public class ConfigStart extends AbstractEvent {

    public ConfigStart(RawMessage msg, Device receiver, Device srcDevice) {
	super(msg, srcDevice, receiver);
    }

    public EventType getType() {
	return EventType.CONFIG_START;
    }

    @Override
    public String toString() {
	return "ConfigStart [msg=" + msg + "]";
    }

}
