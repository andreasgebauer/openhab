package de.gebauer.homematic.hmsecsc;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.AbstractEvent;
import de.gebauer.homematic.msg.MessageType;

public class ShutterStateEvent extends AbstractEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private boolean closed;
    private short msgCount;

    public ShutterStateEvent(RawMessage msg, AbstractDevice srcDevice, AbstractDevice receiver, short channel, int state, short msgCount) {
	super(msg, srcDevice, receiver, channel);
	this.msgCount = msgCount;
	this.closed = state == 0 ? true : false;
    }

    public boolean isClosed() {
	return closed;
    }

    public MessageType getType() {
	return MessageType.SENSOR;
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "WindowStateEvent [#" + msgCount + " closed=" + closed + "; msg=" + getRawMessage() + "]";
    }

}
