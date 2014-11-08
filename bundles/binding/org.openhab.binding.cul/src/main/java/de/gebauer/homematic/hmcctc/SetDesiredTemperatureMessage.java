package de.gebauer.homematic.hmcctc;

import java.math.BigDecimal;

import de.gebauer.homematic.msg.AbstractMessage;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.MessageType;

/**
 * set desired temperature:
 * 
 * value: x°C -> value=x*2 <br>
 * 0 - 100 °C .5°C step
 * 
 * A 0C 06 A0 11 13C86D 1EA6D2 020228 17
 * 
 * 
 * @author andi
 */
public class SetDesiredTemperatureMessage extends AbstractMessage {

    private short value;

    public SetDesiredTemperatureMessage(AbstractMessageParameter param, BigDecimal temp) {
	super(param);
	this.value = temp.multiply(new BigDecimal(2)).shortValue();
    }

    public SetDesiredTemperatureMessage(AbstractMessageParameter param, short value) {
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
	return "SetDesiredTemperatureMessage [chnl=" + getChannel() + ", value=" + value + ", msg=" + getRawMessage() + "]";
    }

}
