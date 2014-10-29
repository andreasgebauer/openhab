package de.gebauer.homematic.msg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public abstract class AbstractMessage implements Message, Serializable {

    private static final long serialVersionUID = 1L;

    private Message answer;
    private Message request;
    private Calendar timestamp;

    private AbstractMessageParameter parameterObject;

    public AbstractMessage(AbstractMessageParameter parameterObject) {
	this.parameterObject = parameterObject;
	this.timestamp = Calendar.getInstance();
    }

    @Override
    public boolean isBroadCast() {
	return BROAD_CAST_ADDRESS.equals(this.parameterObject.msg.getDst());
    }

    @Override
    public int getCount() {
	if (this.parameterObject.msg.getMsgCount() != null) {
	    return Integer.valueOf(this.parameterObject.msg.getMsgCount(), 16);
	}
	return -1;
    }

    @Override
    public RawMessage getRawMessage() {
	return this.parameterObject.msg;
    }

    @Override
    public AbstractDevice getSource() {
	return this.parameterObject.src;
    }

    @Override
    public AbstractDevice getDestination() {
	return this.parameterObject.dst;
    }

    @Override
    public void setRawMessage(final RawMessage msg) {
	this.parameterObject.msg = msg;
    }

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " [msg=" + this.parameterObject.msg + "]";
    }

    @Override
    public short getChannel() {
	return this.parameterObject.channel;
    }

    @Override
    public void setResponse(Message answer) {
	this.answer = answer;
	((AbstractMessage) answer).request = this;
    }

    @Override
    public Message getRequest() {
	return this.request;
    }

    @Override
    public boolean hasAck() {
	boolean hasAnswer = this.answer != null;
	if (hasAnswer && answer instanceof AckStatusEvent) {
	    // return ((AckStatusMessage) answer).getSuccess() != null ? ((AckStatusMessage) answer).getSuccess() : true;
	    return true;
	}

	return hasAnswer;
    }

    @Override
    public Calendar getTimestamp() {
	return this.timestamp;
    }

    @Override
    public BigDecimal getRSSI() {
	return this.parameterObject.rssi;
    }

    public String sendString() {
	String dest = isBroadCast() ? "BROADCAST" : this.getDestination() != null ? this.getDestination().getName() : this.getRawMessage().getDst();
	return "(" + this.getSource().getName() + "->" + dest + " #" + String.format("%02X", this.getCount()) + ")";
    }
}
