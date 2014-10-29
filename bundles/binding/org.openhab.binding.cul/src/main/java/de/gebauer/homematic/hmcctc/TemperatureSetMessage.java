package de.gebauer.homematic.hmcctc;

import java.math.BigDecimal;

import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.InfoActuatorStatusMessage;
import de.gebauer.homematic.msg.Message;

public class TemperatureSetMessage extends InfoActuatorStatusMessage implements Message {

    private final BigDecimal desiredTemp;

    public TemperatureSetMessage(AbstractMessageParameter param, BigDecimal desiredTemp) {
	super(param);
	this.desiredTemp = desiredTemp;
    }

    @Override
    public String toString() {
	return "TemperaturSetMessage [desiredTemp=" + getDesiredTemp() + ", chStatus=" + chStatus + ", msg=" + getRawMessage() + "]";
    }

    public BigDecimal getDesiredTemp() {
	return desiredTemp;
    }

}
