package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * Message type '12'<br>
 * Message flag 'A1'
 * 
 * @author andreas
 *
 */
public class HaveDataMessage extends AbstractMessage {

    public HaveDataMessage(RawMessage msg, AbstractDevice src, AbstractDevice dst) {
	super(new AbstractMessageParameter(msg, src, dst, null));
    }

    @Override
    public MessageType getType() {
	return MessageType.COMMAND2;
    }

    @Override
    public String getPayload() {
	return "";
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "HaveDataMessage [msg=" + getRawMessage() + "]";
    }

}
