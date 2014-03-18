package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * Abstract class for config commands having peer adress, peer channel and parameter list.<br>
 * 
 * <pre>
 * payload ::= &lt;channel&gt;&lt;subtype&gt;&lt;peerAddress&gt;&lt;peerChannel&gt;&lt;paramList&gt;&lt;&gt;
 * </pre>
 * 
 * @author andi
 * 
 */
public abstract class AbstractConfigMessage extends AbstractMessage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected final String peerAddress;
    protected final short peerChannel;
    protected final short paramList;

    public AbstractConfigMessage(final AbstractMessageParameter msgParam, final String peerAddress, final short peerChannel, final short paramList) {
	super(msgParam);
	// byte 2 from payload
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
	return String.format("%02X%02X%s%02X%02X", this.channel, this.getSubType(), this.peerAddress, this.peerChannel, this.paramList);
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    public String getPeerId() {
	return this.peerAddress + String.format("%02X", peerChannel);
    }

    public short getPeerList() {
	return this.paramList;
    }

    public short getPeerChannel() {
	return this.peerChannel;
    }

    /**
     * byte #2 of payload
     * 
     * @return
     */
    public abstract short getSubType();

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " [paramList=" + paramList + ", peerAddress=" + peerAddress + ", peerChannel=" + peerChannel + ", subType="
		+ getSubType() + ", msg=" + msg + ", channel=" + channel + "]";
    }

}
