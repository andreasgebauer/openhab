package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * ..03......
 * 
 * @author andi
 * 
 */
public class ConfigPeerListMessage extends AbstractConfigMessage {

    public ConfigPeerListMessage(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short channel, String peerAddress, short peerChannel,
	    short paramList) {
	super(msg, srcDevice, dstDevice, channel, peerAddress, peerChannel, paramList);
    }

    @Override
    public short getSubType() {
	return 0x03;
    }

}
