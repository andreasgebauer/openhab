package de.gebauer.homematic.hmcctc;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.AbstractEvent;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * Broadcast event.
 * 
 * @author andi
 * 
 */
public class WeatherEvent extends AbstractEvent {

    private float temperature;
    private int humidity;

    public WeatherEvent(RawMessage msg, AbstractDevice sender, AbstractDevice receiver, float temperature, int humidity) {
	super(msg, sender, receiver, (short) -1);
	this.temperature = temperature;
	this.humidity = humidity;
    }

    public MessageType getType() {
	return MessageType.THSENSOR;
    }

    public float getTemperature() {
	return temperature;
    }

    public int getHumidity() {
	return humidity;
    }

    @Override
    public String toString() {
	return "WeatherEvent [" + temperature + "°C " + humidity + "%, raw=" + super.msg + "]";
    }

    @Override
    public boolean needsAck() {
	return false;
    }

}
