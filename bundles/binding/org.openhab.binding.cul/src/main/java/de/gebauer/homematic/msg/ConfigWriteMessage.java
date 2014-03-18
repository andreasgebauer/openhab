package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * 
 * ..08.... ccttdddd
 * 
 * @author andi
 * 
 */
public class ConfigWriteMessage extends AbstractMessage implements Message {

    private String data;
    private short subType;

    public ConfigWriteMessage(AbstractMessageParameter msgParam, String data) {
	super(msgParam);
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
