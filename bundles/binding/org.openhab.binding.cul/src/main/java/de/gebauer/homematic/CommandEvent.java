package de.gebauer.homematic;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public class CommandEvent extends AbstractMessage implements Message {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String payload;

    public CommandEvent(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short channel, String payload) {
	super(msg, srcDevice, dstDevice, channel);
	this.payload = payload;
    }

    @Override
    public MessageType getType() {
	return MessageType.COMMAND;
    }

    @Override
    public String getPayload() {
	throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public boolean needsAck() {
	return false;
    }

}
