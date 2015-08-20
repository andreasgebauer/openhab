package org.openhab.binding.cec.internal.protocol.data;

public enum FeatureOpcode implements Identifiable {
	SET_MENU_LANGUAGE(0x32),
	GIVE_DEVICE_VENDOR_ID(0x8C),
	VENDOR_COMMAND_WITH_ID(0xA0);

	private final int value;

	private FeatureOpcode(int value) {
		this.value = value;
	}

	public int getId() {
		return value;
	}

	public static FeatureOpcode valueOf(int id) {
		for (FeatureOpcode status : values()) {
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
