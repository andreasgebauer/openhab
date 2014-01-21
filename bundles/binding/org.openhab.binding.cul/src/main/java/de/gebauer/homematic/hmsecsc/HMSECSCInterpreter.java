package de.gebauer.homematic.hmsecsc;

import static de.gebauer.cul.homematic.in.MessageInterpreter.toInt;
import static de.gebauer.cul.homematic.in.MessageInterpreter.toShort;
import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageType;
import de.gebauer.homematic.msg.StatusChangeEvent;
import de.gebauer.homematic.msg.StatusChangeEvent.ChannelStatus;

public class HMSECSCInterpreter implements DeviceMessageInterpreter {

    // Pairing HMCCTC with HMSECSC
    // [1EA808->000000 #CD; len=1A, flag=VAL_84, type=UNKNOWN, p=2100394A4551303730393932325800FFFF]
    // [2190C5->000000 #07; len=1A, flag=VAL_84, type=UNKNOWN, p=21002F4B45513031363434363380810101]
    // [1EA808->2190C5 #CE; len=1A, flag=VAL_A0, type=UNKNOWN, p=2100394A45513037303939323258000300]
    // [2190C5->1EA808 #CE; len=0A, flag=VAL_80, type=ACK, p=00]
    // [1EA808->2190C5 #CF; len=10, flag=VAL_A0, type=CONFIG, p=00051EA8080300]
    // [2190C5->1EA808 #CF; len=0A, flag=VAL_80, type=ACK, p=00]
    // [1EA808->2190C5 #D0; len=0D, flag=VAL_A0, type=CONFIG, p=00080901]
    // [2190C5->1EA808 #D0; len=0A, flag=VAL_80, type=ACK, p=00]
    // [1EA808->2190C5 #D1; len=0B, flag=VAL_A0, type=CONFIG, p=0006]
    // [2190C5->1EA808 #D1; len=0A, flag=VAL_80, type=ACK, p=00]
    // [1EA808->2190C5 #D2; len=10, flag=VAL_A0, type=CONFIG, p=01051EA8080304]
    // [2190C5->1EA808 #D2; len=0A, flag=VAL_80, type=ACK, p=00]
    // [1EA808->2190C5 #D3; len=0D, flag=VAL_A0, type=CONFIG, p=01080101]
    // [2190C5->1EA808 #D3; len=0A, flag=VAL_80, type=ACK, p=00]
    // [1EA808->2190C5 #D4; len=0B, flag=VAL_A0, type=CONFIG, p=0106]
    // [2190C5->1EA808 #D4; len=0A, flag=VAL_80, type=ACK, p=00]
    // [2190C5->1EA808 #08; len=10, flag=VAL_A0, type=CONFIG, p=03052190C50103]
    // [1EA808->2190C5 #08; len=0A, flag=VAL_80, type=ACK, p=80]

    // Pairing CCU with HMSECSC
    // 1A E4 84 00 2190C5 000000 21 002F4B45513031363434363380810101
    // 10 E5 A0 01 13C86E 2190C5 00 050000000000
    // 0A E5 80 02 2190C5 13C86E 00
    // 13 E6 A0 01 13C86E 2190C5 00 0802010A130BC80C6E
    // 0A E6 80 02 2190C5 13C86E 00
    // 0B E7 A0 01 13C86E 2190C5 00 06
    // 0A E7 80 02 2190C5 13C86E 00

    @Override
    public Message read(RawMessage msg, AbstractDevice src, AbstractDevice dst) {
	if (MessageType.SENSOR == msg.getMsgType()) {
	    String payload = msg.getPayload();
	    // 01 EC00
	    // 01 EDC8

	    short channel = toShort(payload, 0, 2);
	    short msgCount = toShort(payload, 2, 2);
	    int state = toInt(payload, 4, 2);

	    return new ShutterStateEvent(msg, src, dst, channel, state, msgCount);
	} else if (MessageType.SWITCH == msg.getMsgType()) {
	    // when battery lid is opened
	    // messageFlag: A6
	    // Battery
	    // 06 01 0000: lid closed, sensor closed
	    // 06 01 000E: lid open, sensor closed
	    // 06 01 C800 lid closed, sensor open
	    // 06 01 C80E lid open, sensor open

	    // lid binary sensor binary
	    // 1100 1000 0000 1110

	    short sensorVal = toShort(msg.getPayload(), 4, 2);
	    short lidVal = toShort(msg.getPayload(), 6, 2);
	    return new StatusChangeEvent(msg, src, dst, new ShutterStateData(sensorVal == 0xC8, lidVal == 0x0E), (short) 1);
	}
	return null;
    }

}
