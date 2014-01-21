package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * SubType: 0x02
 * 
 * 
 * <br>
 * <br>
 * send from TC to VD when going to unpair.
 * 
 * @author andi
 * 
 */
public class Config2Message extends AbstractConfigMessage implements Message {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Config2Message(final RawMessage msg, final AbstractDevice srcDevice, final AbstractDevice dstDevice, final short channel,
	    final String peerAddress, final short peerChannel, final short paramList) {
	super(msg, srcDevice, dstDevice, channel, peerAddress, peerChannel, paramList);
    }

    @Override
    public short getSubType() {
	return 0x02;
    }

}
