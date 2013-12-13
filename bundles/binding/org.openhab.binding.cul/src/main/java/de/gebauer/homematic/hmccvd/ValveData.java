package de.gebauer.homematic.hmccvd;

import de.gebauer.homematic.DeviceState;

public class ValveData implements DeviceState {

    public enum MotorError {
	/**
	 * 00
	 */
	OK,
	/**
	 * 01
	 */
	BLOCKED,
	/**
	 * 02
	 */
	LOOSE,
	/**
	 * 03
	 */
	ADJUSTING_RANGE_TO_SMALL
    }

    private MotorState motorState;
    private short position;
    private short offset;
    private short errorPosition;
    private MotorError motorError;
    private BatteryStatus batteryStatus;

    /**
     * 
     * @author andi
     * 
     */
    public enum MotorState {
	STOP,
	CLOSING,
	OPENING
    }

    public void setMotorState(MotorState stop) {
	this.motorState = stop;
    }

    public MotorState getMotorState() {
	return this.motorState;
    }

    public void setPosition(short vp) {
	this.position = vp;
    }

    public void setOffset(short off) {
	this.offset = off;
    }

    public void setErrorPosition(short vep) {
	this.errorPosition = vep;
    }

    public void setMotorError(MotorError motorError) {
	this.motorError = motorError;
    }

    public void setBatteryStatus(BatteryStatus ok) {
	this.batteryStatus = ok;
    }

    @Override
    public String toString() {
	return "VDValveData [motor: " + motorError + " " + motorState + " " + position + "% errPos:"
		+ (int) errorPosition + "% off:" + offset + "% bat:" + batteryStatus + "]";
    }
}
