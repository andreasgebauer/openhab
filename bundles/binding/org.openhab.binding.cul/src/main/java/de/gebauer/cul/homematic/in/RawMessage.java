package de.gebauer.cul.homematic.in;

public class RawMessage {

    public String length;
    public String msgCount;
    public String msgFlag;
    public String msgType;
    public String src;
    public String dst;
    public String p;

    public int getCount() {
	return MessageInterpreter.toInt(msgCount);
    }

    @Override
    public String toString() {
	return "RawMessage [length=" + length + ", msgCount=" + msgCount + ", msgFlag=" + msgFlag + ", msgType="
		+ msgType + ", src=" + src + ", dst=" + dst + ", p=" + p + "]";
    }

}
