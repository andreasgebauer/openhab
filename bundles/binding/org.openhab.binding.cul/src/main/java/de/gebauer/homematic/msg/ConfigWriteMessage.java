package de.gebauer.homematic.msg;

/**
 * ConfigMessage with subtyp 0x08 <br>
 * <br>
 * ..08.... ccttdddd
 * 
 * @author andi
 * 
 */
public class ConfigWriteMessage extends AbstractConfigMessage implements Message {

    private String data;

    public ConfigWriteMessage(AbstractMessageParameter msgParam, String data) {
	super(msgParam);
	this.data = data;
    }

    @Override
    public String getPayload() {
	return String.format("%02X%02X%s", getChannel(), getSubType(), data);
    }

    @Override
    public String toString() {
	return "ConfigWriteCommand [data=" + data + ", subType=" + getSubType() + ", msg=" + getRawMessage() + ", channel=" + getChannel() + "]";
    }

    @Override
    public short getSubType() {
	return 0x08;
    }

}
