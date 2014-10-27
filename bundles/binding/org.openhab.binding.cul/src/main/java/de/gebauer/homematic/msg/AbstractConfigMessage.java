package de.gebauer.homematic.msg;

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
    protected PeerData peerData = new PeerData();
    protected Short paramList;

    public AbstractConfigMessage(final AbstractMessageParameter msgParam, final String peerAddress, final short peerChannel, final short paramList) {
	super(msgParam);
	// byte 2 from payload
	this.peerData.peerAddress = peerAddress;
	this.peerData.peerChannel = peerChannel;
	this.paramList = paramList;
    }

    public AbstractConfigMessage(final AbstractMessageParameter msgParam) {
	super(msgParam);
    }

    @Override
    public MessageType getType() {
	return MessageType.CONFIG;
    }

    @Override
    public String getPayload() {
	if (peerData != null) {
	    return String.format("%02X%02X%s%02X%02X", this.getChannel(), this.getSubType(), this.peerData.peerAddress, this.peerData.peerChannel,
		    this.paramList);
	}

	return String.format("%02X%02X", this.getChannel(), this.getSubType());
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    public String getPeerId() {
	return this.peerData.peerAddress + String.format("%02X", peerData.peerChannel);
    }

    public short getPeerList() {
	return this.paramList;
    }

    public short getPeerChannel() {
	return this.peerData.peerChannel;
    }

    /**
     * byte #2 of payload
     * 
     * @return
     */
    public abstract short getSubType();

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " [paramList=" + paramList + ", peerAddress=" + peerData.peerAddress + ", peerChannel=" + peerData.peerChannel
		+ ", subType="
		+ getSubType() + ", msg=" + this.getRawMessage() + ", channel=" + this.getChannel() + "]";
    }

}
