package de.gebauer.homematic;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public abstract class AbstractCommand extends AbstractMessage {

    public AbstractCommand(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice) {
	super(msg, srcDevice, dstDevice, (short) -1);
    }

}
