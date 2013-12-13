package de.gebauer.homematic;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * ccttdddd
 * 
 * @author andi
 * 
 */
public class ConfigWriteCommand extends AbstractMessage implements Message {

    private String data;
    private short subType;

    public ConfigWriteCommand(RawMessage msg, AbstractDevice src, AbstractDevice dst, Short channel, String data) {
	super(msg, src, dst, channel);
	this.data = data;
	this.subType = 0x08;
    }

    @Override
    public MessageType getType() {
	return MessageType.CONFIG;
    }

    @Override
    public String getPayload() {
	return String.format("%02X%02X%s", channel, subType, data);
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "ConfigWriteCommand [data=" + data + ", subType=" + subType + ", msg=" + msg + ", channel=" + channel + "]";
    }

}
