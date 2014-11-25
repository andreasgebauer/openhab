package de.gebauer.homematic.msg;

public enum MessageType {

    /**
     * 0x00
     */
    UNKNOWN(0x00),
    /**
     * 0x01
     */
    CONFIG(0x01),
    /**
     * 0x02
     */
    ACK(0x02),

    /**
     * 0x04
     */
    UNKNOWN2(0x04),

    /**
     * 0x10
     */
    SWITCH(0x10),
    /**
     * 0x11
     */
    COMMAND(0x11),
    /**
     * 0x12
     */
    COMMAND2(0x12),

    /**
     * 0x41
     */
    SENSOR(0x41),
    /**
     * <pre>
     * 0B .. 58 A2 ....... ...... .... 
     * len cnt ty fl src   dest pl
     * </pre>
     * 
     * 0x58
     */
    THERMOSTAT(0x58),
    /**
     * 0x70
     */
    THSENSOR(0x70),

    /**
     * ?
     */
    UNKNOWN1(0x3E),
    /**
     * ?
     */
    UNKNOWN3(0x3F);

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

    public int getInt() {
	return msgType;
    }

    public String getStr() {
	return msgTypeStr;
    }

}
