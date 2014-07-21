package de.gebauer.homematic.msg;

/**
 * MessageType.CONFIG
 * 
 * this.channel, this.subType, this.peerAddress, this.peerChannel, this.paramList
 * 
 * @author andi
 * 
 */
public class ConfigStartMessage extends AbstractConfigMessage {

    public ConfigStartMessage(AbstractMessageParameter msgParam, final String peerAddress, final short peerChannel, final short paramList) {
	super(msgParam, peerAddress, peerChannel, paramList);
    }

    @Override
    public short getSubType() {
	return 0x05;
    }

    @Override
    public String toString() {
	return "ConfigStartCommand [paramList=" + paramList + ", peerAddress=" + peerAddress + ", peerChannel=" + peerChannel
		+ ", msg=" + getRawMessage() + ", channel=" + getChannel() + "]";
    }

}
