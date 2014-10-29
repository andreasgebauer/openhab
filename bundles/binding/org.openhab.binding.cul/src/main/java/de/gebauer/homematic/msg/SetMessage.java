package de.gebauer.homematic.msg;


/**
 * set desired temperature:
 * 
 * value: x°C -> value=x*2 <br>
 * 0 - 100 °C .5°C step
 * 
 * 
 * @author andi
 */
public class SetMessage extends AbstractMessage {

    private short value;

    public SetMessage(AbstractMessageParameter param, short value) {
	super(param);
	this.value = value;
    }

    @Override
    public String getPayload() {
	return String.format("%02X%02X%02X", 0x02, getChannel(), value);
    }

    @Override
    public MessageType getType() {
	return MessageType.COMMAND;
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "SetMessage [chnl=" + getChannel() + ", value=" + value + ", msg=" + getRawMessage() + "]";
    }

}
