package de.gebauer.homematic.hmcctc;

import java.math.BigDecimal;

import de.gebauer.homematic.device.AbstractDevice;

/**
 * Methods to be executed on {@link ThermoControlDevice}.
 * 
 * @author andi
 * 
 */
public interface ThermoControl {

    boolean controlMode(final AbstractDevice src, final ControlMode ctrlMode);

    boolean setTemperature(final AbstractDevice src, final BigDecimal temp);

}
