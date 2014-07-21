package de.gebauer.homematic.hmcctc;

import java.math.BigDecimal;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.AbstractEvent;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.MessageType;

/**
 * Broadcast event.
 * 
 * @author andi
 * 
 */
public class WeatherEvent extends AbstractEvent {

    private BigDecimal temperature;
    private int humidity;

    public WeatherEvent(RawMessage msg, AbstractDevice sender, AbstractDevice receiver, BigDecimal temperature, int humidity, int rssi) {
	super(new AbstractMessageParameter(msg, sender, receiver, (short) -1, rssi));
	this.temperature = temperature;
	this.humidity = humidity;
    }

    @Override
    public MessageType getType() {
	return MessageType.THSENSOR;
    }

    public BigDecimal getTemperature() {
	return temperature;
    }

    public int getHumidity() {
	return humidity;
    }

    @Override
    public String toString() {
	return "Weather " + super.sendString() + " [" + temperature + "°C " + humidity + "%]";
    }

    @Override
    public boolean needsAck() {
	return false;
    }

}
