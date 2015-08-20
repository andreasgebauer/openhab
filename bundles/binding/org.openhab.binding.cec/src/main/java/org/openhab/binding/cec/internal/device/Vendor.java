package org.openhab.binding.cec.internal.device;

import org.openhab.binding.cec.internal.protocol.data.Identifiable;

public enum Vendor implements Identifiable {

	SAMSUNG("SAMSUNG", 0x0000F0),
	PULSEEIGHT("PULSEEIGHT", 0x001582),
	GOOGLE("GOOGLE", 0x001A11);

	private int id;
	private String name;

	private Vendor(final String name, final int id) {
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static Vendor valueOf(final int vendorVal) {
		Vendor[] values;
		for (int length = (values = values()).length, i = 0; i < length; ++i) {
			final Vendor vendor = values[i];
			if (vendor.id == vendorVal) {
				return vendor;
			}
		}
		return null;
	}

	public static int getLength() {
		return 3;
	}
}
