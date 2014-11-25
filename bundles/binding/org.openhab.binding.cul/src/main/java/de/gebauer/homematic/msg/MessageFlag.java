package de.gebauer.homematic.msg;

public enum MessageFlag {

    VAL_00((short) 0x00),
    VAL_80((short) 0x80),
    VAL_82((short) 0x82),
    VAL_84((short) 0x84),
    VAL_86((short) 0x86),
    VAL_A0((short) 0xA0),
    VAL_A1((short) 0xA1),
    VAL_A2((short) 0xA2),
    VAL_A4((short) 0xA4),
    VAL_A6((short) 0xA6),

    ;

    public final short val;

    private MessageFlag(short val) {
	this.val = val;
    }

    public static MessageFlag forRaw(String msgFlag) {
	for (MessageFlag flag : values()) {
	    if (flag.val == Short.valueOf(msgFlag, 16)) {
		return flag;
	    }
	}
	return null;
    }

}
