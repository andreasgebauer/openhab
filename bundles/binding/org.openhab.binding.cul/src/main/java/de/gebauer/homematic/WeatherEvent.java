package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public class WeatherEvent extends AbstractEvent {

    private float temperature;
    private int humidity;

    public WeatherEvent(RawMessage msg, Device sender, Device receiver, float temperature, int humidity) {
	super(msg, sender, receiver);
	this.temperature = temperature;
	this.humidity = humidity;
    }

    public EventType getType() {
	return EventType.WEATHER;
    }

    public float getTemperature() {
	return temperature;
    }

    public int getHumidity() {
	return humidity;
    }

    @Override
    public String toString() {
	return "WeatherEvent [temperature=" + temperature + ", humidity=" + humidity + ", raw=" + super.msg + "]";
    }

}
