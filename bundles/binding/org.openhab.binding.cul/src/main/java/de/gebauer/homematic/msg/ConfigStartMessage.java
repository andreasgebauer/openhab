package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * MessageType.CONFIG
 * 
 * this.channel, this.subType, this.peerAddress, this.peerChannel, this.paramList
 * 
 * @author andi
 * 
 */
public class ConfigStartMessage extends AbstractConfigMessage {

    public ConfigStartMessage(final RawMessage msg, final AbstractDevice srcDevice, final AbstractDevice dstDevice, final short channel,
	    final String peerAddress, final short peerChannel, final short paramList) {
	super(msg, srcDevice, dstDevice, channel, peerAddress, peerChannel, paramList);
    }

    @Override
    public short getSubType() {
	return 0x05;
    }

    @Override
    public String toString() {
	return "ConfigStartCommand [paramList=" + paramList + ", peerAddress=" + peerAddress + ", peerChannel=" + peerChannel
		+ ", msg=" + msg + ", channel=" + channel + "]";
    }

}
