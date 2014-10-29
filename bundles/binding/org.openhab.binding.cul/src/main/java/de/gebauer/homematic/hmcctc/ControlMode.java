package de.gebauer.homematic.hmcctc;

public enum ControlMode {
    CENTRAL(0),
    AUTO(1),
    MANUAL(2),
    PARTY(3);

    private final int val;

    ControlMode(int val) {
	this.val = val;
    }

    public static ControlMode valueOf(int i) {
	for (ControlMode mode : values()) {
	    if (mode.getVal() == i) {
		return mode;
	    }
	}
	return null;
    }

    public int getVal() {
	return val;
    }

}
