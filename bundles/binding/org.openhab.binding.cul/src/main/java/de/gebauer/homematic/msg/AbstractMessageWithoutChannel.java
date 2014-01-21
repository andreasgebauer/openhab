package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * Command without channel.
 * 
 * @author andi
 * 
 */
public abstract class AbstractMessageWithoutChannel extends AbstractMessage {

    public AbstractMessageWithoutChannel(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice) {
	this(msg, srcDevice, dstDevice, (short) -1);
    }

    public AbstractMessageWithoutChannel(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short chnl) {
	super(msg, srcDevice, dstDevice, chnl);
    }

    @Override
    public boolean needsAck() {
	return true;
    }
}
