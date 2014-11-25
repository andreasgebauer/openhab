package de.gebauer.homematic.msg;


/**
 * ..03......
 * 
 * @author andi
 * 
 */
public class ConfigPeerListMessage extends AbstractConfigMessage {

    public ConfigPeerListMessage(AbstractMessageParameter msgParam, String peerAddress, short peerChannel,
	    short paramList) {
	super(msgParam, peerAddress, peerChannel, paramList);
    }

    @Override
    public short getSubType() {
	return 0x03;
    }

}
