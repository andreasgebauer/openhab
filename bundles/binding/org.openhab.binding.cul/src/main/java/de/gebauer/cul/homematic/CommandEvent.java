package de.gebauer.cul.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.AbstractEvent;
import de.gebauer.homematic.Event;
import de.gebauer.homematic.EventType;

public class CommandEvent extends AbstractEvent implements Event {

    private String payload;

    public CommandEvent(RawMessage msg, Device srcDevice, Device dstDevice, String payload) {
	super(msg, srcDevice, dstDevice);
	this.payload = payload;
    }

    @Override
    public EventType getType() {
	return EventType.UNKNOW;
    }

}
