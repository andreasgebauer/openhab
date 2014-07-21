package de.gebauer.cul.homematic.in;

import static de.gebauer.cul.homematic.in.MessageInterpreter.toShort;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageType;
import de.gebauer.homematic.msg.SetMessage;

public class CCUInterpreter implements DeviceMessageInterpreter {

    // set desired temperature (20°C) on HMCCTC
    // 09 08 A1 12 13C86C 1EA808 -> COMMAND2
    // 0A 08 80 02 1EA808 13C86C 00 -> ACK
    // 0C 09 A0 11 13C86C 1EA808 02 02 28 -> COMMAND
    // 0E 09 80 02 1EA808 13C86C 01 02 28 003C -> ACK

    // set desired temperature (6°C) on HMCCTC
    // 0C 09 A0 11 13C86C 1EA808 02 02 0C -> COMMAND
    // 0E 09 80 02 1EA808 13C86C 01 02 0C 0038 -> ACK

    // set desired temperature (7°C) on HMCCTC
    // 0C 09 A0 11 13C86C 1EA808 02 02 0E -> COMMAND
    // 0E 09 80 02 1EA808 13C86C 01 02 0E 0038 -> ACK

    @Override
    public Message read(RawMessage m, AbstractDevice src, AbstractDevice dst) {
	if (MessageType.COMMAND == m.getMsgType()) {
	    // set dim factor
	    // 02 01 00 0320FFFF 0%

	    // set desired temp

	    // 02 02 0E
	    // channel: 02

	    // temp:
	    // 0 = off = 0°C
	    // 0C = 6°C
	    // 0E = 7°C
	    // 28 = 20°C
	    // C8 = on = 100°C

	    short chnl = toShort(m.getPayload(), 2, 2);

	    if (m.getPayload().length() >= 6) {
		short value = toShort(m.getPayload(), 4, 2);
		return new SetMessage(m, src, dst, chnl, value);
	    }
	} else if (MessageType.COMMAND2 == m.getMsgType()) {
	    // there is no payload!
	    // 09 0C A1 12 13C86C 1EA808
	    // flag: A1
	    if (m.getPayload().length() >= 2) {
		short channel = toShort(m.getPayload(), 2, 2);
		// return new ConfigStartCommand(m, src, dst, channel, (short) -1);
	    }
	}
	return null;
    }

}
