package de.gebauer.homematic;

import java.io.Serializable;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public abstract class AbstractMessage implements Message, Serializable {

    protected RawMessage msg;
    private final AbstractDevice src;
    private final AbstractDevice dst;
    protected Short channel;
    private Message answer;

    public AbstractMessage(final RawMessage msg, final AbstractDevice src, final AbstractDevice dst, final Short channel) {
	this.msg = msg;
	this.src = src;
	this.dst = dst;
	this.channel = channel;
    }

    @Override
    public boolean isBroadCast() {
	return this.msg.getDst().equals(BROAD_CAST_ADDRESS);
    }

    @Override
    public int getCount() {
	return Integer.valueOf(this.msg.getMsgCount(), 16);
    }

    @Override
    public RawMessage getRawMessage() {
	return this.msg;
    }

    @Override
    public AbstractDevice getSender() {
	return this.src;
    }

    @Override
    public AbstractDevice getDestination() {
	return this.dst;
    }

    @Override
    public void setRawMessage(final RawMessage msg) {
	this.msg = msg;
    }

    @Override
    public String toString() {
	return this.msg.getMsgType() + " [msg=" + this.msg + "]";
    }

    @Override
    public int getChannel() {
	return this.channel;
    }

    @Override
    public boolean hasAck() {
	return this.answer != null;
    }

    public void setAnswer(Message answer) {
	this.answer = answer;
    }
}
