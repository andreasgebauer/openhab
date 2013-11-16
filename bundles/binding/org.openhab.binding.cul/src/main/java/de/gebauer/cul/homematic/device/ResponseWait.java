package de.gebauer.cul.homematic.device;

import de.gebauer.cul.homematic.in.PendType;

public class ResponseWait {

    private int msgId;
    private PendType pendingType;
    private int channel;

    public int getMsgId() {
	return this.msgId;
    }

    public PendType getPending() {
	return this.pendingType;
    }

    public int getChannel() {
	return this.channel;
    }

    public boolean isPending() {
	return pendingType != null;
    }
}
