package de.gebauer.homematic;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * cctt
 */
public class ConfigEndCommand extends AbstractMessage implements Message {

    private short subType;

    public ConfigEndCommand(RawMessage raw, AbstractDevice src, AbstractDevice dst, short channel) {
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
