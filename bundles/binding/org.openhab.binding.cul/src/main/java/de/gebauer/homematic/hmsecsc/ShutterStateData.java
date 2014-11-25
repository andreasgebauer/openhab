package de.gebauer.homematic.hmsecsc;

import de.gebauer.homematic.DeviceState;

public class ShutterStateData implements DeviceState {

    private boolean lidOpen;
    private boolean sensorOpen;

    public ShutterStateData(boolean sensor, boolean lid) {
	this.sensorOpen = sensor;
	this.lidOpen = lid;
    }

    public boolean isLidOpen() {
        return lidOpen;
    }

    public boolean isSensorOpen() {
        return sensorOpen;
    }

}
