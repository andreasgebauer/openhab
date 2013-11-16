package de.gebauer.cul.homematic.device;

public enum Model {
    HMLCSW1PLOM54("HM-LC-SW1-PL-OM54", 0x0001, ""),
    HMCCVD("HM-CC-VD", 0x003A, "c:w"),
    HMCCTC("HM-CC-TC", 0x0039, "c:w"),
    HMSECSC("HM-SEC-SC", 0x002F, "c:w"),
    HMLCDIM1TPI2("HM-LC-DIM1T-PI2", 0x00A4, "c:w");

    private final String name;
    private final int id;
    private String rxt;

    Model(final String name, final int id, final String rxt) {
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

}
