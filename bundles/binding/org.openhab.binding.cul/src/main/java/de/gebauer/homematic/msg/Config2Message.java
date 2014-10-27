package de.gebauer.homematic.msg;

/**
 * SubType: 0x02
 * 
 * 
 * <br>
 * <br>
 * sent from TC to VD when going to unpair.
 * 
 * @author andi
 * 
 */
public class Config2Message extends AbstractConfigMessage implements Message {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Config2Message(AbstractMessageParameter msgParam, final String peerAddress, final short peerChannel, final short paramList) {
	super(msgParam, peerAddress, peerChannel, paramList);
    }

    @Override
    public short getSubType() {
	return 0x02;
    }

}
