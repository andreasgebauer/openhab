package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

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
    private short subType;

    public ConfigEndMessage(RawMessage raw, AbstractDevice src, AbstractDevice dst, short channel) {
	super(raw, src, dst, channel);
	this.subType = 0x06;
    }

    @Override
    public MessageType getType() {
	return MessageType.CONFIG;
    }

    @Override
    public String getPayload() {
	return String.format("%02X%02X", channel, subType);
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "ConfigEndCommand [subType=" + subType + ", msg=" + msg + ", channel=" + channel + "]";
    }

}
