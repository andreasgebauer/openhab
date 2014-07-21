package de.gebauer.homematic.hmcctc;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.WeekDay;
import de.gebauer.homematic.device.AbstractDevice;
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

    protected DisplayMode dm;
    private DisplayTemp dt;
    private TemperatureUnit tempUnit;
    private ControlMode controlMode;
    private WeekDay decalcDay;

    public BasicTCInfoMessage(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short channel, DisplayMode dm, DisplayTemp dt,
	    TemperatureUnit tempUnit, ControlMode ctrlMode, WeekDay decalcDay) {
	super(msg, srcDevice, dstDevice, channel);
	this.dm = dm;
	this.dt = dt;
	this.tempUnit = tempUnit;
	this.controlMode = ctrlMode;
	this.decalcDay = decalcDay;
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

    public ControlMode getControlMode() {
	return controlMode;
    }

    @Override
    public String toString() {
	return "BasicTCInfo [dm=" + dm + ", dt=" + dt + ", tempUnit=" + tempUnit + ", ctrlMode=" + controlMode + ", decalcDay=" + decalcDay + ", msg="
		+ getRawMessage() + ", channel=" + getChannel() + "]";
    }

}
