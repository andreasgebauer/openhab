package de.gebauer.homematic.hmcctc;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.AbstractEvent;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.WeekDay;
import de.gebauer.homematic.device.AbstractDevice;

public class BasicTCInfo extends AbstractEvent implements Message {

    protected DisplayMode dm;
    private DisplayTemp dt;
    private TemperatureUnit tempUnit;
    private ControlMode ctrlMode;
    private WeekDay decalcDay;

    public BasicTCInfo(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short channel, DisplayMode dm, DisplayTemp dt,
	    TemperatureUnit tempUnit, ControlMode ctrlMode, WeekDay decalcDay) {
	super(msg, srcDevice, dstDevice, channel);
	this.dm = dm;
	this.dt = dt;
	this.tempUnit = tempUnit;
	this.ctrlMode = ctrlMode;
	this.decalcDay = decalcDay;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    @Override
    public String toString() {
	return "BasicTCInfo [dm=" + dm + ", dt=" + dt + ", tempUnit=" + tempUnit + ", ctrlMode=" + ctrlMode + ", decalcDay=" + decalcDay + ", msg=" + msg
		+ ", channel=" + channel + "]";
    }

    @Override
    public String getPayload() {
	return String.format("04%02X0000000005%02X%02X", channel);
    }

    @Override
    public boolean needsAck() {
	return true;
    }

}
