package de.gebauer.homematic.msg;

import java.math.BigDecimal;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public class AbstractMessageParameter {

    public RawMessage msg;
    public final AbstractDevice src;
    public final AbstractDevice dst;
    public final Short channel;
    public final BigDecimal rssi;

    /**
     * 
     * @param msg
     *            the raw message
     * @param src
     *            the source
     * @param dst
     *            the destination
     * @param channel
     *            the channel
     * @param rssi
     *            the RSSI value
     */
    public AbstractMessageParameter(RawMessage msg, AbstractDevice src, AbstractDevice dst, Short channel, BigDecimal rssi) {
	this.msg = msg;
	this.src = src;
	this.dst = dst;
	this.channel = channel;
	this.rssi = rssi;
    }

    public AbstractMessageParameter(RawMessage msg, AbstractDevice src, AbstractDevice dst, Short channel) {
	this(msg, src, dst, channel, null);
    }
}