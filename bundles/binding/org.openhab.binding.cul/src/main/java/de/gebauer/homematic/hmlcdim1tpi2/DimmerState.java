package de.gebauer.homematic.hmlcdim1tpi2;

import de.gebauer.homematic.DeviceState;

public class DimmerState implements DeviceState {

    private int val;
    private boolean overload;
    private boolean overheat;
    private boolean reduced;

    public DimmerState(int val, boolean overload, boolean overheat, boolean reduced) {
	this.val = val;
	this.overload = overload;
	this.overheat = overheat;
	this.reduced = reduced;
    }

    public int getVal() {
	return val;
    }

    public boolean isOverload() {
	return overload;
    }

    public boolean isOverheat() {
	return overheat;
    }

    public boolean isReduced() {
	return reduced;
    }
}
