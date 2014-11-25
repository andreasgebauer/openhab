package de.gebauer.homematic.hmlcdim1tpi2;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
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
    private int state;
    private int subType;

    public DimmerStateChangeEvent(AbstractMessageParameter abstractMessageParameter, int state, int subType) {
	super(abstractMessageParameter);
	this.state = state;
	this.subType = subType;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    public int getState() {
	return state;
    }

    public int getDontknow1() {
	return subType;
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    @Override
    public String toString() {
	return "DimmerStateChangeEvent [msg=" + getRawMessage() + ", state=" + state + ", subType=" + subType + ", chnl="
		+ getChannel() + "]";
    }

}
