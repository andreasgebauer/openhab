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

    public AbstractMessageWithoutChannel(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, int rssi) {
	this(msg, srcDevice, dstDevice, (short) -1, rssi);
    }

    public AbstractMessageWithoutChannel(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short chnl, int rssi) {
	super(new AbstractMessageParameter(msg, srcDevice, dstDevice, chnl, rssi));
    }

    @Override
    public boolean needsAck() {
	return true;
    }

}
