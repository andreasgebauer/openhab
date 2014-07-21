package de.gebauer.homematic.msg;

/**
 * Simple message having two bytes payload.<br>
 * Subtype: 06 <br>
 * 
 * <pre>
 * payload ::= &lt;channel&gt; 06
 * </pre>
 * 
 * 
 */
public class ConfigEndMessage extends AbstractMessage implements Message {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final short subType;

    public ConfigEndMessage(AbstractMessageParameter msgParam) {
	super(msgParam);
	this.subType = 0x06;
    }

    @Override
    public MessageType getType() {
	return MessageType.CONFIG;
    }

    @Override
    public String getPayload() {
	return String.format("%02X%02X", getChannel(), subType);
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "ConfigEndCommand [subType=" + subType + ", msg=" + getRawMessage() + ", channel=" + getChannel() + "]";
    }

}
