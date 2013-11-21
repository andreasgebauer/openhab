package de.gebauer.cul.homematic.device;

import de.gebauer.homematic.HomeMaticDeviceType;

public enum Model {
    HMLCSW1PLOM54(null, "HM-LC-SW1-PL-OM54", 0x0001, ""),
    HMCCVD(HomeMaticDeviceType.THERMOSTAT, "HM-CC-VD", 0x003A, "c:w"),
    HMCCTC(HomeMaticDeviceType.THERMOSTAT, "HM-CC-TC", 0x0039, "c:w"),
    HMSECSC(HomeMaticDeviceType.THREE_STATE_SENSOR, "HM-SEC-SC", 0x002F, "c:w"),
    HMLCDIM1TPI2(null, "HM-LC-DIM1T-PI2", 0x00A4, "c:w");

    private final String name;
    private final int id;
    private String rxt;
    private HomeMaticDeviceType deviceType;

    Model(final HomeMaticDeviceType deviceType, final String name, final int id, final String rxt) {
	this.deviceType = deviceType;
	this.name = name;
	this.id = id;
	this.rxt = rxt;
    }

    public static Model forId(final int id) {
	for (final Model model : Model.values()) {
	    if (model.id == id) {
		return model;
	    }
	}
	return null;
    }

    public String getName() {
	return name;
    }

    public String getRxType() {
	return rxt;
    }

    public HomeMaticDeviceType getDeviceType() {
	return this.deviceType;
    }

}
