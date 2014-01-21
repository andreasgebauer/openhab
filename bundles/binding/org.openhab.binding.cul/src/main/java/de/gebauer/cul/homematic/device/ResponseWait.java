package de.gebauer.cul.homematic.device;

import de.gebauer.cul.homematic.in.PendType;
import de.gebauer.homematic.msg.Message;

public class ResponseWait {

    private PendType pendingType;
    private short channel;
    private short list;
    private String peerId;
    private Message cmd;

    public PendType getPending() {
	return this.pendingType;
    }

    public int getChannel() {
	return this.channel;
    }

    public boolean isPending() {
	return pendingType != null;
    }

    public void setPending(PendType pendType) {
	pendingType = pendType;
    }

    public void forChannel(short chn) {
	channel = chn;
    }

    public short forChannel() {
	return this.channel;
    }

    public void forList(short list) {
	this.list = list;
    }

    public short forList() {
	return this.list;
    }

    public void forPeer(String peerId) {
	this.peerId = peerId;
    }

    public void forCmd(Message cmd) {
	this.cmd = cmd;
    }

}
