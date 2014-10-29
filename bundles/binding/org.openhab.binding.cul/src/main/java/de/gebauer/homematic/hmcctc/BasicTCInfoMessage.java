package de.gebauer.homematic.hmcctc;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.WeekDay;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.hmcctc.HMCCTCInterpreter.Time;
import de.gebauer.homematic.msg.AbstractEvent;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageType;

/**
 * 04020000000005(..)(..)(....)$
 * 
 * @author andi
 * 
 */
public class BasicTCInfoMessage extends AbstractEvent implements Message {

    public static class BasicTCData {

	DisplayMode displayMode;
	DisplayTemp displayTemp;
	TemperatureUnit displayTempUnit;
	ControlMode controlMode;
	WeekDay decalcDay;
	Time decalcTime;

	public BasicTCData(DisplayMode displayMode, DisplayTemp displayTemp, TemperatureUnit tempUnit, ControlMode ctrlMode, WeekDay decalcDay, Time decalcTime) {
	    this.displayMode = displayMode;
	    this.displayTemp = displayTemp;
	    this.displayTempUnit = tempUnit;
	    this.controlMode = ctrlMode;
	    this.decalcDay = decalcDay;
	    this.decalcTime = decalcTime;
	}

    }

    private BasicTCData tcData;

    public BasicTCInfoMessage(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short channel, BasicTCData tcData) {
	super(msg, srcDevice, dstDevice, channel);
	this.tcData = tcData;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    @Override
    public String getPayload() {
	return String.format("04%02X0000000005%02X%02X", getChannel());
    }

    @Override
    public boolean needsAck() {
	return true;
    }

    public BasicTCData getTcData() {
	return tcData;
    }

    @Override
    public String toString() {
	return "BasicTCInfoMessage [getRawMessage()=" + getRawMessage() + ", tcData=" + tcData + ", getChannel()=" + getChannel() + "]";
    }

}
