package de.gebauer.homematic.device;

import java.lang.reflect.Method;

import de.gebauer.cul.homematic.in.CCUInterpreter;
import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;

public class VirtualCCU extends AbstractDevice {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean pairingEnabled;
    private String hmPairSerial = "";

    private DeviceMessageInterpreter interpreter = new CCUInterpreter();

    public VirtualCCU(final String hmId) {
	super(hmId, hmId, null);
    }

    public void setPairingEnabled(final boolean enabled) {
	this.pairingEnabled = enabled;
    }

    public boolean isPairingEnabled() {
	return this.pairingEnabled;
    }

    public String getHmPairSerial() {
	return this.hmPairSerial;
    }

    public void setHmPairSerial(final String serialNo) {
	this.hmPairSerial = serialNo;
    }

    @Override
    public Method[] getCommands() {
	return null;
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return this.interpreter;
    }

}
