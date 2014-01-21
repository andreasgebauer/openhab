package de.gebauer.homematic.hmcctc;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.InfoActuatorStatusMessage;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.StatusChangeEvent.ChannelStatus;

public class TemperaturSetMessage extends InfoActuatorStatusMessage implements Message {

    private double desiredTemp;

    public TemperaturSetMessage(RawMessage msg, AbstractDevice src, AbstractDevice dst, double desiredTemp, ChannelStatus chStatus) {
	super(msg, src, dst, (short) 0x02, chStatus);
	this.desiredTemp = desiredTemp;
    }

    @Override
    public String toString() {
	return "TemperaturSetCommand [desiredTemp=" + desiredTemp + ", chStatus=" + chStatus + ", msg=" + msg + "]";
    }

}
