package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public class WindowStateEvent extends AbstractEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private boolean closed;

    public WindowStateEvent(RawMessage msg, Device srcDevice, Device receiver, int state) {
	super(msg, srcDevice, receiver);
	this.closed = state == 0 ? true : false;
    }

    public boolean isClosed() {
	return closed;
    }

    public EventType getType() {
	return EventType.WINDOW;
    }

    @Override
    public String toString() {
	return "WindowStateEvent [closed=" + closed + "; msg=" + msg + "]";
    }

}
