package org.openhab.binding.cec.internal.protocol.data;

public enum DeckStatus implements Identifiable {

	PLAY(0x11),
	RECORD(0x12),
	PLAY_REVERSE(0x13),
	STILL(0x14),
	SLOW(0x15),
	SLOW_REVERSE(0x16),
	FAST_FORWARD(0x17),
	FAST_REVERSE(0x18),
	NO_MEDIA(0x19),
	STOP(0x1A),
	SKIP_FORWARD(0x1B),
	SKIP_REVERSE(0x1C),
	INDEX_SEARCH_FORWARD(0x1D),
	INDEX_SEARCH_REVERSE(0x1E),
	OTHER_STATUS(0x1F),
	//
	;

	private final int value;

	private DeckStatus(int value) {
		this.value = value;
	}

	public int getId() {
		return value;
	}

	public static DeckStatus valueOf(int id) {
		for (DeckStatus status : values()) {
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
