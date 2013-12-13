package de.gebauer.cul.homematic.in;

import de.gebauer.homematic.MessageFlag;
import de.gebauer.homematic.MessageType;

public class RawMessageBuilder {

    private String length;
    private String msgCount;
    private String msgFlag;
    private String msgType;
    private String src;
    private String dst;
    private String p;

    public RawMessageBuilder() {
    }

    public RawMessage build() {
	RawMessage rawMessage = new RawMessage();
	rawMessage.setLength(this.length);
	rawMessage.setMsgCount(this.msgCount);
	rawMessage.setMsgType(this.msgType);
	rawMessage.setSrc(this.src);
	rawMessage.setDst(this.dst);
	rawMessage.setP(this.p);
	rawMessage.setMsgFlag(this.msgFlag);
	return rawMessage;
    }

    public RawMessageBuilder setPayload(String payload) {
	this.p = payload;
	return this;
    }

    public RawMessageBuilder setLength(String length) {
	this.length = length;
	return this;
    }

    public RawMessageBuilder setMsgCount(String count) {
	this.msgCount = count;
	return this;
    }

    public RawMessageBuilder setMsgFlag(MessageFlag flag) {
	setMsgFlag(String.format("%02X", flag.val));
	return this;
    }

    public RawMessageBuilder setMsgFlag(String flag) {
	this.msgFlag = flag;
	return this;
    }

    public RawMessageBuilder setMsgType(MessageType type) {
	this.msgType = type.getStr();
	return this;
    }

    public RawMessageBuilder setMsgType(String messageType) {
	this.msgType = messageType;
	return this;
    }

    public RawMessageBuilder setSrc(String src) {
	this.src = src;
	return this;
    }

    public RawMessageBuilder setDst(String destination) {
	this.dst = destination;
	return this;
    }
}
