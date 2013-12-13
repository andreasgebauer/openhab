package de.gebauer.homematic;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * 
 * @author andi
 * 
 */
public class ConfigStartCommand extends AbstractMessage {

    private final short paramList;
    private final int peerAddress;
    private final short peerChannel;
    private final short subType;

    public ConfigStartCommand(final RawMessage msg, final AbstractDevice srcDevice, final AbstractDevice dstDevice, final short channel, final int peerAddress,
	    final short peerChannel, final short paramList) {
	super(msg, srcDevice, dstDevice, channel);
	// byte 2 from payload
	this.subType = 0x05;
	this.peerAddress = peerAddress;
	this.peerChannel = peerChannel;
	this.paramList = paramList;
    }

    @Override
    public MessageType getType() {
	return MessageType.CONFIG;
    }

    @Override
    public String getPayload() {
	return String.format("%02X%02X%06X%02X%02X", this.channel, this.subType, this.peerAddress, this.peerChannel, this.paramList);
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "ConfigStartCommand [paramList=" + paramList + ", peerAddress=" + peerAddress + ", peerChannel=" + peerChannel + ", subType=" + subType
		+ ", msg=" + msg + ", channel=" + channel + "]";
    }

}
