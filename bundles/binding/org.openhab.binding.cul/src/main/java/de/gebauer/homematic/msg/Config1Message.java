package de.gebauer.homematic.msg;

/**
 * Config message with sub type 0x01<br>
 * 
 * @author andreas
 *
 */
public class Config1Message extends AbstractConfigMessage {

    public Config1Message(AbstractMessageParameter msgParam) {
	super(msgParam);
    }

    @Override
    public short getSubType() {
	return 0x01;
    }

}
