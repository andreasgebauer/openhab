package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * sub type: 04<br>
 * 
 * @author andi
 * 
 */
public class ParamChangeMessage extends AbstractMessage implements Message {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String peerId;
    private int list;
    private String data;

    public ParamChangeMessage(RawMessage msg, AbstractDevice src, AbstractDevice dst, short channel, String peerId, int list, String data) {
	super(msg, src, dst, channel);
	this.peerId = peerId;
	this.list = list;
	this.data = data;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String getPayload() {
	return null;
    }

    @Override
    public String toString() {
	return "ParamChangeMessage [peerId=" + peerId + ", list=" + list + ", data=" + data + ", msg=" + msg + ", channel=" + channel + "]";
    }

}
