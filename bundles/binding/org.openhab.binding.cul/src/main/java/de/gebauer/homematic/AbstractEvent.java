package de.gebauer.homematic;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public abstract class AbstractEvent extends AbstractMessage {

    public AbstractEvent(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short channel) {
	super(msg, srcDevice, dstDevice, channel);
    }

    @Override
    public String getPayload() {
	// we don't need a payload
	return null;
    }
}
