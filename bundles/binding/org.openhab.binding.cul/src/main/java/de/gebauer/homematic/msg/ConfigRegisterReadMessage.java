package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * 0x04<br>
 * <br>
 * MessageType.CONFIG<br>
 * 
 * ccttppppppccll<br>
 * 
 * 0104...<br>
 * 
 * this.channel, this.subType, this.peerAddress, this.peerChannel, this.paramList
 * 
 * @author andi
 * 
 */
public class ConfigRegisterReadMessage extends AbstractConfigMessage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ConfigRegisterReadMessage(AbstractMessageParameter msgParam, final String peerAddress, final short peerChannel, final short paramList) {
	super(msgParam, peerAddress, peerChannel, paramList);
    }

    @Override
    public short getSubType() {
	return 0x04;
    }

}
