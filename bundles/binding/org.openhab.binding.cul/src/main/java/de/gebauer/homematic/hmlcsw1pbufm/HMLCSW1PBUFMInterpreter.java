package de.gebauer.homematic.hmlcsw1pbufm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.DeviceState;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.AckStatusEvent;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageFlag;
import de.gebauer.homematic.msg.MessageType;

public class HMLCSW1PBUFMInterpreter implements DeviceMessageInterpreter {

    public static final class SwitchState implements DeviceState {

	private boolean on;

	public SwitchState(boolean on) {
	    this.on = on;
	}

	public boolean isOn() {
	    return on;
	}
    }

    private static final Logger LOG = LoggerFactory.getLogger(HMLCSW1PBUFMInterpreter.class);

    @Override
    public Message read(RawMessage m, AbstractDevice src, AbstractDevice dst) {
	LOG.debug("Interpreting " + m);

	// Notify OFF UNPAIRED
	// [209DA1->000000 #11; len=0D, flag=VAL_84, type=SWITCH, p=06 01 00 00 0F]

	// Notify ON UNPAIRED
	// [209DA1->000000 #13; len=0D, flag=VAL_84, type=SWITCH, p=06 01 C8 00 05]

	// Notify OFF PAIRED
	// [209DA1->13C86D #13; len=1D, flag=VAL_A4, type=SWITCH, p=06 01 00 A8 96 7FECAB3030313735100101297E713E1C]

	// Notify ON PAIRED
	// [209DA1->13C86D #0C; len=0E, flag=VAL_A4, type=SWITCH, p=06 01 C8 00 4A 24]

	final String payload = m.getPayload();

	if (m.getMsgType() == MessageType.SWITCH) {
	    if (m.getMsgFlag() == MessageFlag.VAL_84 || m.getMsgFlag() == MessageFlag.VAL_A4) {
		final int state = MessageInterpreter.toInt(payload, 4, 2);
		final int rssi = MessageInterpreter.toInt(payload, 8, 2);

		final AbstractMessageParameter msgParam = new AbstractMessageParameter(m, src, dst, (short) 1, rssi);

		return new SwitchStateMessage(msgParam, new SwitchState(state == 0xC8));
	    }
	} else if (m.getMsgType() == MessageType.ACK) {
	    if (m.getMsgFlag() == MessageFlag.VAL_80) {
		// 0E xx 80 02 209DA1 13C86D 0101 C800 4413

		final short chnl = MessageInterpreter.toShort(payload, 2, 2);
		final int state = MessageInterpreter.toInt(payload, 4, 2);
		final int rssi = MessageInterpreter.toInt(payload, 8, 2);

		return new AckStatusEvent(m, src, dst, chnl, rssi, new SwitchState(state == 0xC8));
	    }
	}

	return null;
    }

}
