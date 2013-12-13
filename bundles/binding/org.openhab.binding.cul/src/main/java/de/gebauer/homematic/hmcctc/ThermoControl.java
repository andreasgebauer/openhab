package de.gebauer.homematic.hmcctc;

import de.gebauer.homematic.device.AbstractDevice;

public interface ThermoControl {

    boolean controlMode(final AbstractDevice src, final ControlMode ctrlMode);

}
