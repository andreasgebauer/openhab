package de.gebauer.homematic.hmlcdim1tpi2;

import de.gebauer.homematic.msg.AbstractEvent;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageType;

/**
 * Event send when button is pressed on device.
 * 
 * @author andi
 * 
 */
public class DimmerStateChangeEvent extends AbstractEvent implements Message {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private DimmerState state;

    public DimmerStateChangeEvent(AbstractMessageParameter abstractMessageParameter, DimmerState state) {
	super(abstractMessageParameter);
	this.state = state;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    public DimmerState getState() {
	return state;
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "DimmerStateChangeEvent [msg=" + getRawMessage() + ", state=" + state + "]";
    }

}
