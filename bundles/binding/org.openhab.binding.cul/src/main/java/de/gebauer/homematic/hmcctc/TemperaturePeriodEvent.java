package de.gebauer.homematic.hmcctc;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.hmcctc.HMCCTCInterpreter.Time;
import de.gebauer.homematic.msg.AbstractEvent;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageType;

public class TemperaturePeriodEvent extends AbstractEvent implements Message {

    public static class TemperaturePeriod {

	private int hour;
	private int minute;
	private int temp;

	public TemperaturePeriod(int hour, int minute, int temp) {
	    this.hour = hour;
	    this.minute = minute;
	    this.temp = temp;
	}

	public TemperaturePeriod(Time readTime, int i) {
	    
	}

	@Override
	public String toString() {
	    return "[" + hour + ":" + minute + ", temp=" + temp + "]";
	}

	public String getTime() {
	    return this.hour + ":" + (this.minute >= 10 ? this.minute : "0" + this.minute);
	}
    }

    private int day;
    private TemperaturePeriod tPeriod;
    private TemperaturePeriod tPeriod2;

    public TemperaturePeriodEvent(RawMessage msg, AbstractDevice src, AbstractDevice dst, short channel, int day, TemperaturePeriod tPeriod,
	    TemperaturePeriod tPeriod2) {
	super(msg, src, dst, channel);
	this.day = day;
	this.tPeriod = tPeriod;
	this.tPeriod2 = tPeriod2;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    @Override
    public String toString() {
	return "TemperaturePeriodEvent [day=" + day + "," + getString(tPeriod) + "," + getString(tPeriod2) + ", msg="
		+ getRawMessage() + "]";
    }

    private String getString(TemperaturePeriod period) {
	return " to " + period.getTime() + ": " + period.temp + "°C";
    }

    @Override
    public boolean needsAck() {
	return true;
    }

}
