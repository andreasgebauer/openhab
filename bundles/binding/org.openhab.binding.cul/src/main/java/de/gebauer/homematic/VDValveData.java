package de.gebauer.homematic;

public class VDValveData implements DeviceData {

    public enum MotorError {
	OK,
	LOOSE
    }

    private MotorState motorState;
    private int valveOffset;
    private int valveErrorPosition;
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

    public void setValveOffset(int off) {
	this.valveOffset = off;
    }

    public void setValveErrorPosition(int vep) {
	this.valveErrorPosition = vep;
    }

    public MotorError getMotorError() {
	return motorError;
    }

    public void setMotorError(MotorError motorError) {
	this.motorError = motorError;
    }

    public void setBatteryStatus(BatteryStatus ok) {
	this.batteryStatus = ok;
    }

    @Override
    public String toString() {
	return "VDValveData [motor: " + motorError + " " + motorState + " " + valveOffset + "% errPos="
		+ valveErrorPosition + ", battery:" + batteryStatus + "]";
    }

}
