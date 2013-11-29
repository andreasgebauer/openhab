package de.gebauer.cul.homematic;

import de.gebauer.cul.homematic.device.Device;

public class VirtualCCU extends Device {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private boolean pairingEnabled;
    private String hmPairSerial = "";

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

}
