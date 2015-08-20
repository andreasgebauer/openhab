package org.openhab.binding.cec.internal.protocol.data;

public enum StatusRequest {

	ON(0x01),
	OFF(0x02),
	ONCE(0x03),
	//
	;

	private final int value;

	private StatusRequest(int value) {
		this.value = value;
	}

	public int getId() {
		return value;
	}

	public static StatusRequest valueOf(int id) {
		for (StatusRequest status : values()) {
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
