package de.gebauer.cul.homematic.device;

/**********************************************************
 * Defines a HomeMatic device type.
 * 
 * @author Rossi java@rosstauscher.de
 *********************************************************/

public enum HomeMaticDeviceType {

    UNKNOWN("", Direction.SENDER),
    ALARM_CONTROL("01", Direction.CONTROLLER),
    SWITCH("10", Direction.RECEIVER),
    DIMMER("20", Direction.RECEIVER),
    BLIND_ACTUATOR("30", Direction.RECEIVER),
    CLIMATE_CONTROL("39", Direction.RECEIVER),
    REMOTE("40", Direction.SENDER),
    SENSOR("41", Direction.SENDER),
    SWI("42", Direction.SENDER),
    PUSH_BUTTON("43", Direction.SENDER),
    THERMOSTAT("58", Direction.RECEIVER),
    KFM100("60", Direction.SENDER),
    TH_SENSOR("70", Direction.SENDER),
    THREE_STATE_SENSOR("80", Direction.SENDER),
    MOTION_DETECTOR("81", Direction.SENDER),
    KEY_MATIC("C0", Direction.SENDER),
    WIN_MATIC("C1", Direction.RECEIVER),
    SMOKE_DETECTOR("CD", Direction.SENDER);

    public enum Direction {
	SENDER, RECEIVER, CONTROLLER
    }

    private String id;
    private Direction direction;;

    /**********************************************************
     * Constructor
     * 
     * @param id
     *            of the device type.
     * @param direction
     *            the device.
     *********************************************************/

    HomeMaticDeviceType(String id, Direction direction) {
	this.id = id;
	this.direction = direction;
    }

    /**********************************************************
     * @return the id
     *********************************************************/

    public String getId() {
	return id;
    }

    /**********************************************************
     * @return the direction
     *********************************************************/

    public Direction getDirection() {
	return direction;
    }

    /**********************************************************
     * Gets the device type for a given device type hex ID.
     * 
     * @param id
     *            of the device type.
     * @return the HomeMaticDeviceType or UNKNOWN if not a known code.
     *********************************************************/

    public static HomeMaticDeviceType valueOfID(String id) {
	for (HomeMaticDeviceType t : HomeMaticDeviceType.values()) {
	    if (t.getId().equalsIgnoreCase(id)) {
		return t;
	    }
	}
	return UNKNOWN;
    }

}
