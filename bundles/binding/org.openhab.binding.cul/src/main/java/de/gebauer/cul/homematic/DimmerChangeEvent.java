package de.gebauer.cul.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.AbstractEvent;
import de.gebauer.homematic.Event;
import de.gebauer.homematic.EventType;

public class DimmerChangeEvent extends AbstractEvent implements Event {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int state;
    private int dontknow1;
    private int dontknow2;

    public DimmerChangeEvent(RawMessage msg, Device srcDevice, Device dstDevice, int state, int dontknow1, int dontknow2) {
	super(msg, srcDevice, dstDevice);
	this.state = state;
	this.dontknow1 = dontknow1;
	this.dontknow2 = dontknow2;
    }

    @Override
    public EventType getType() {
	return EventType.STATUS_CHANGE;
    }

    public int getState() {
	return state;
    }

    public int getDontknow1() {
	return dontknow1;
    }

    public int getDontknow2() {
	return dontknow2;
    }

    @Override
    public String toString() {
	return "DimmerChangeEvent [state=" + state + ", dontknow1=" + dontknow1 + ", dontknow2=" + dontknow2 + "]";
    }

}
