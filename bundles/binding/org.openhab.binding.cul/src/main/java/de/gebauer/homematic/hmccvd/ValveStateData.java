package de.gebauer.homematic.hmccvd;

import de.gebauer.homematic.DeviceState;

public class ValveStateData implements DeviceState {

    private short position;
    private BatteryStatus batteryStatus;
    private MotorError motorError;
    private MotorState motorState;

    public ValveStateData(final short vp, final BatteryStatus batteryStatus, final MotorError motorError, final MotorState motorState) {
	this.position = vp;
	this.batteryStatus = batteryStatus;
	this.motorError = motorError;
	this.motorState = motorState;
    }

    public MotorState getMotorState() {
	return this.motorState;
    }

    public short getPosition() {
	return this.position;
    }

    public BatteryStatus getBatteryStatus() {
	return this.batteryStatus;
    }

    public MotorError getMotorError() {
	return this.motorError;
    }

    @Override
    public String toString() {
	return "ValveData [motor: " + this.motorError + " " + this.motorState + " " + this.position + " bat:" + this.batteryStatus + "]";
    }

}
