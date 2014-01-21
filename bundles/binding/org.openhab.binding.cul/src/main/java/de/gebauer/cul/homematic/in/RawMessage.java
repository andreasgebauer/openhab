package de.gebauer.cul.homematic.in;

import de.gebauer.homematic.msg.MessageFlag;
import de.gebauer.homematic.msg.MessageType;

/**
 * len cnt flag type src dst p
 * 
 * @author andi
 * 
 */
public class RawMessage {

    private String length;
    private String msgCount;
    private String msgFlag;
    private String msgType;
    private String src;
    private String dst;
    private String p;

    public RawMessage() {
    }

    public int getCount() {
	return MessageInterpreter.toInt(getMsgCount());
    }

    @Override
    public String toString() {
	return "[" + getSrc() + "->" + getDst() + " #" + getMsgCount() + "; len=" + getLength() + ", flag="
		+ getMsgFlag() + ", type="
		+ getMsgType() + ", p=" + getPayload() + "]";
    }

    public MessageType getMsgType() {
	if (msgType == null) {
	    return null;
	}
	return MessageType.forRaw(msgType);
    }

    void setMsgType(String msgType) {
	this.msgType = msgType;
    }

    public MessageFlag getMsgFlag() {
	if (msgFlag == null) {
	    return null;
	}
	return MessageFlag.forRaw(msgFlag);
    }

    void setMsgFlag(String msgFlag) {
	this.msgFlag = msgFlag;
    }

    void setLength(String length) {
	this.length = length;
    }

    void setMsgCount(String msgCount) {
	this.msgCount = msgCount;
    }

    void setSrc(String src) {
	this.src = src;
    }

    void setDst(String dst) {
	this.dst = dst;
    }

    void setP(String p) {
	this.p = p;
    }

    public String getLength() {
	return length;
    }

    public String getMsgCount() {
	return msgCount;
    }

    public String getSrc() {
	return src;
    }

    public String getDst() {
	return dst;
    }

    public String getPayload() {
	return p;
    }

}
