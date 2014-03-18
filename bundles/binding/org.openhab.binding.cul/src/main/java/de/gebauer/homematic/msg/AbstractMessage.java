package de.gebauer.homematic.msg;

import java.io.Serializable;
import java.util.Calendar;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public abstract class AbstractMessage implements Message, Serializable {

    private static final long serialVersionUID = 1L;

    protected RawMessage msg;
    private final AbstractDevice src;
    private final AbstractDevice dst;
    protected Short channel;
    private Message answer;
    private Message request;
    private Calendar timestamp;

    public AbstractMessage(AbstractMessageParameter parameterObject) {
	this.msg = parameterObject.msg;
	this.src = parameterObject.src;
	this.dst = parameterObject.dst;
	this.channel = parameterObject.channel;
	this.timestamp = Calendar.getInstance();
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
    public AbstractDevice getSource() {
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
	return this.getClass().getSimpleName() + " [msg=" + this.msg + "]";
    }

    @Override
    public short getChannel() {
	return this.channel;
    }

    public void setResponse(Message answer) {
	this.answer = answer;
    }

    @Override
    public Message getRequest() {
	return this.request;
    }

    @Override
    public boolean hasAck() {
	boolean hasAnswer = this.answer != null;
	if (hasAnswer && answer instanceof AckStatusMessage) {
	    return ((AckStatusMessage) answer).getSuccess() != null ? ((AckStatusMessage) answer).getSuccess() : true;
	}

	return hasAnswer;
    }

    @Override
    public void setRequest(Message request) {
	this.request = request;
    }

    @Override
    public Calendar getTimestamp() {
	return this.timestamp;
    }

}
