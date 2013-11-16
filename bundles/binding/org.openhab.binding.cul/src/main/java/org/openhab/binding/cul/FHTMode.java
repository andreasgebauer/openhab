package org.openhab.binding.cul;

import org.openhab.core.types.Command;

public enum FHTMode implements Command {

	AUTO("00"), MANUAL("02"), VACATION("01");

	private String hexValue;

	private FHTMode(String value) {
		this.hexValue = value;
	}

	public String getValue() {
		return hexValue;
	}

	@Override
	public String format(String pattern) {
		return String.format(pattern, toString());
	}
}
