package de.gebauer.homematic.device;

public enum Model {

    /**
     * ?
     */
    CCU(null, "CUL", 0x0001, ""),

    /**
     * Light control. Switch.
     */
    HMLCSW1PLOM54(null, "HM-LC-SW1-PL-OM54", 0x0001, ""),
    
    /**
     * Light control. Switch.
     */
    HMLCSW1PBUFM(HomeMaticDeviceType.SWITCH, "HM-LC-Sw1PBU-FM", 0x0069, ""),
    /**
     * Heating Valve Device.
     */
    HMCCVD(HomeMaticDeviceType.THERMOSTAT, "HM-CC-VD", 0x003A, "c:w"),
    /**
     * Thermo Control.
     */
    HMCCTC(HomeMaticDeviceType.THERMOSTAT, "HM-CC-TC", 0x0039, "c:w"),
    /**
     * Window / Door Closing.
     */
    HMSECSC(HomeMaticDeviceType.THREE_STATE_SENSOR, "HM-Sec-SC", 0x002F, "c:w"),
    /**
     * Light Control. Dimmer.
     */
    HMLCDIM1TPI2(HomeMaticDeviceType.DIMMER, "HM-LC-DIM1T-PI2", 0x00A4, "c:w");

    private HomeMaticDeviceType deviceType;
    private final String name;
    private final int id;
    private String rxt;

    /**
     * 
     * @param deviceType
     *            the device type
     * @param name
     *            the model name
     * @param id
     *            the id
     * @param rxt
     */
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

    public int getId() {
	return this.id;
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

    public String getCycle() {
	return null;
    }

}
