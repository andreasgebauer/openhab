package de.gebauer.homematic;

public enum MessageType {

    /**
     * 0x00
     */
    UNKNOWN(0x00),
    /**
     * 0x02
     */
    ACK(0x02),
    /**
     * 0x10
     */
    STATUS_CHANGE(0x10),
    /**
     * 0x58
     */
    CLIMATE(0x58),
    /**
     * 0x41
     */
    WINDOW(0x41),
    /**
     * 0x70
     */
    WEATHER(0x70);

    private int msgType;
    private String msgTypeStr;

    MessageType(int msgType) {
	this.msgType = msgType;
	this.msgTypeStr = String.format("%02X", msgType);
    }

    public static MessageType forRaw(String msgType) {
	return forInt(Integer.valueOf(msgType, 16));
    }

    public static MessageType forInt(int msgType) {
	for (MessageType mType : MessageType.values()) {
	    if (mType.msgType == msgType) {
		return mType;
	    }
	}
	return null;
    }

    public boolean is(String msgType) {
	return this.msgTypeStr.equals(msgType);
    }
}
