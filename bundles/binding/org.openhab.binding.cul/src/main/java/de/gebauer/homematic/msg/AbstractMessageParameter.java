package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public class AbstractMessageParameter {
    public RawMessage msg;
    public AbstractDevice src;
    public AbstractDevice dst;
    public Short channel;

    public AbstractMessageParameter(RawMessage msg, AbstractDevice src, AbstractDevice dst, Short channel) {
	this.msg = msg;
	this.src = src;
	this.dst = dst;
	this.channel = channel;
    }
}