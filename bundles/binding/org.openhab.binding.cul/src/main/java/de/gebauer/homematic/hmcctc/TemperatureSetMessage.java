package de.gebauer.homematic.hmcctc;

import java.math.BigDecimal;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.InfoActuatorStatusMessage;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.StatusChangeEvent.ChannelStatus;

public class TemperatureSetMessage extends InfoActuatorStatusMessage implements Message {

    private final BigDecimal desiredTemp;

    public TemperatureSetMessage(RawMessage msg, AbstractDevice src, AbstractDevice dst, BigDecimal desiredTemp, ChannelStatus chStatus) {
	super(msg, src, dst, chStatus);
	this.desiredTemp = desiredTemp;
    }

    @Override
    public String toString() {
	return "TemperaturSetCommand [desiredTemp=" + getDesiredTemp() + ", chStatus=" + chStatus + ", msg=" + getRawMessage() + "]";
    }

    public BigDecimal getDesiredTemp() {
	return desiredTemp;
    }

}
