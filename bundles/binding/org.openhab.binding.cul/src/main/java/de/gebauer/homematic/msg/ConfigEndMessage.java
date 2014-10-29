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
public class ConfigEndMessage extends AbstractConfigMessage implements Message {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ConfigEndMessage(AbstractMessageParameter msgParam) {
	super(msgParam);
    }

    @Override
    public short getSubType() {
	return 0x06;
    }

    @Override
    public String getPayload() {
	return String.format("%02X%02X", this.getChannel(), this.getSubType());
    }

    @Override
    public String toString() {
	return "ConfigEndCommand [subType=" + getSubType() + ", msg=" + getRawMessage() + ", channel=" + getChannel() + "]";
    }

}
