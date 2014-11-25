package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public class AbstractMessageParameter {

    public RawMessage msg;
    public final AbstractDevice src;
    public final AbstractDevice dst;
    public final Short channel;
    public final int rssi;

    public AbstractMessageParameter(RawMessage msg, AbstractDevice src, AbstractDevice dst, Short channel, int rssi) {
	this.msg = msg;
	this.src = src;
	this.dst = dst;
	this.channel = channel;
	this.rssi = rssi;
    }

    public AbstractMessageParameter(RawMessage msg, AbstractDevice src, AbstractDevice dst, Short channel) {
	this(msg, src, dst, channel, -1);
    }
}