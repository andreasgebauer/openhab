package de.gebauer.homematic.hmccvd;

import de.gebauer.homematic.DeviceState;

public class ValveConfigData implements DeviceState {

    private short offset;
    private short errorPosition;

    public ValveConfigData(final short off, final short vep) {
	this.offset = off;
	this.errorPosition = vep;
    }

    public short getErrorPosition() {
	return errorPosition;
    }

    public short getOffset() {
	return offset;
    }
}
