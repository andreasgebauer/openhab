package de.gebauer.homematic.hmcctc;

import java.math.BigDecimal;

import de.gebauer.homematic.DeviceState;

final class DesiredTemperatureState implements DeviceState {

    private BigDecimal desiredTemp;

    /**
     */
    DesiredTemperatureState(BigDecimal desiredTemp) {
	this.desiredTemp = desiredTemp;
    }

    public BigDecimal getDesiredTemperature() {
	return this.desiredTemp;
    }
}