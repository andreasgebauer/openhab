package org.openhab.binding.cec.internal.protocol.data;

public enum PowerStatus implements Identifiable {
	ON(0x00),
	STANDBY(0x01),
	IN_TRANSITION_STANDBY_TO_ON(0x02),
	IN_TRANSITION_ON_TO_STANDBY(0x03);

	private int value;

	private PowerStatus(int value) {
		this.value = value;
	}

	public int getId() {
		return value;
	}

	public static PowerStatus valueOf(int id) {
		for (PowerStatus status : values()) {
			if (status.value == id) {
				return status;
			}
		}
		return null;
	}

	public static int getLength() {
		return 1;
	}

}
