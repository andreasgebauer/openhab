package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * Is answer to {@link ConfigRegisterReadMessage}.<br>
 * 
 * <pre>
 * &lt;payload> := &lt;format>[&lt;address>:&lt;data>]*<br>
 * format := 02|03<br>
 * address := ..<br>
 * data := :short|:long<br>
 * short := ..<br>
 * long := ....<br>
 * <br>
 * ff aa:dd aa:dd ...<br>
 * ff aa:dddd<br>
 * </pre>
 * 
 * <br>
 * answer to config register read command when pairing vd with tc:<br>
 * [13C86E->1C475A #2C; len=10, flag=VAL_A0, type=CONFIG, p=01040000000005]<br>
 * [1C475A->13C86E #2C; len=10, flag=VAL_80, type=SWITCH, p=0209000A0F0000]<br>
 * 02 0900 0A0F 0000<br>
 * ??<br>
 * 03 ......<br>
 * 
 * @author andi
 * 
 */
public class ParamResponseMessage extends AbstractMessage implements Message {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String data;
    private String format;

    public ParamResponseMessage(RawMessage msg, AbstractDevice src, AbstractDevice dst, String format, String data) {
	super(new AbstractMessageParameter(msg, src, dst, null));
	this.format = format;
	this.data = data;
    }

    @Override
    public MessageType getType() {
	return MessageType.SWITCH;
    }

    @Override
    public String getPayload() {
	return null;
    }

    @Override
    public boolean needsAck() {
	return false;
    }

    public String getData() {
	return this.data;
    }

    @Override
    public String toString() {
	return "ParamResponseMessage [msg=" + getRawMessage() + ", format=" + format + ", data=" + data + "]";
    }

}
