package org.openhab.binding.cec.internal.protocol.data;

public enum AbortReason implements Identifiable {

	UNRECOGNIZED_OPCODE(0x00),
	REFUSED(0x04),

	;
	private final int value;

	private AbortReason(int value) {
		this.value = value;
	}

	public int getId() {
		return value;
	}

	public static AbortReason valueOf(int id) {
		for (AbortReason status : values()) {
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
