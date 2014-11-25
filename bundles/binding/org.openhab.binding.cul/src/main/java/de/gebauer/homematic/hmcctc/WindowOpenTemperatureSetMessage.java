package de.gebauer.homematic.hmcctc;

import java.math.BigDecimal;

import de.gebauer.homematic.msg.AbstractMessage;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageType;

public class WindowOpenTemperatureSetMessage extends AbstractMessage implements Message {

    private BigDecimal temp;
    private String actorId;

    public WindowOpenTemperatureSetMessage(AbstractMessageParameter parameterObject, String actorId, BigDecimal temp) {
	super(parameterObject);
	this.actorId = actorId;
	this.temp = temp;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    @Override
    public String getPayload() {
	return null;
    }

    @Override
    public boolean needsAck() {
	return false;
    }

    public BigDecimal getTemperature() {
	return this.temp;
    }

}
