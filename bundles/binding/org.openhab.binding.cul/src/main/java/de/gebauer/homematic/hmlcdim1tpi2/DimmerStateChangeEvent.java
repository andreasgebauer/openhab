package de.gebauer.homematic.hmlcdim1tpi2;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.AbstractEvent;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.device.AbstractDevice;

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

    public DimmerStateChangeEvent(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, int state, int subType, short chnl) {
	super(msg, srcDevice, dstDevice, chnl);
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
	return "DimmerStateChangeEvent [msg=" + msg + ", state=" + state + ", subType=" + subType + ", chnl="
		+ channel + "]";
    }

}
