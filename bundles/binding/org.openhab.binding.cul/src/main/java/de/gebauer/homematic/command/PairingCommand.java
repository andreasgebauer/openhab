package de.gebauer.homematic.command;

import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.ConfigEndMessage;
import de.gebauer.homematic.msg.ConfigStartMessage;
import de.gebauer.homematic.msg.ConfigWriteMessage;
import de.gebauer.homematic.msg.MessageFlag;

public class PairingCommand extends AbstractCommand {

    /**
     * Common pairing command.
     * 
     * @param ccu
     * @param src
     */
    public PairingCommand(VirtualCCU ccu, AbstractDevice src) {
	final RawMessageBuilder msgBuilder = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_A0);

	short channel = 0;

	short pChnl = (short) 0;
	AbstractMessageParameter msgParam = new AbstractMessageParameter(msgBuilder.build(), ccu, src, channel);

	add(new ConfigStartMessage(msgParam, "000000", pChnl, pChnl));
	createConfigMessages(msgParam);
	add(new ConfigEndMessage(msgParam));
    }

    public PairingCommand() {
    }

    private void createConfigMessages(AbstractMessageParameter msgParam) {
	// common pairing
	String content = "0201";
	int s = 0xA;

	for (int i = 0; i < 3; i++) {
	    content += String.format("%02X", s++);
	    content += msgParam.src.getId().substring(i * 2, i * 2 + 2);
	}
	for (int l = 0; l < content.length(); l += 28) {
	    int ml = content.length() - l < 28 ? content.length() - l : 8;
	    add(new ConfigWriteMessage(msgParam, content.substring(l, ml)));
	}

    }

    @Override
    public Integer getCountForce() {
	return null;
    }

}
