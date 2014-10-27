package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public class CommandMessage extends AbstractMessage implements Message {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String payload;

    public CommandMessage(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short channel, String payload) {
	super(new AbstractMessageParameter(msg, srcDevice, dstDevice, channel));
	this.payload = payload;
    }

    @Override
    public MessageType getType() {
	return MessageType.COMMAND;
    }

    @Override
    public String getPayload() {
	return this.payload;
    }

    @Override
    public boolean needsAck() {
	return true;
    }

}
