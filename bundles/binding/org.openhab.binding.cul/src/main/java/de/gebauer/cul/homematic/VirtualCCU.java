package de.gebauer.cul.homematic;

import de.gebauer.cul.homematic.device.Device;

public class VirtualCCU extends Device {

    private final String hmId;
    private boolean pairingEnabled;
    private String hmPairSerial = "";

    public VirtualCCU(final String hmId) {
	super(hmId, hmId, null);
	this.hmId = hmId;
    }

    public String getHmId() {
	return this.hmId;
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
