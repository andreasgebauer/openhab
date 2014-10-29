package de.gebauer.homematic.hmlcdim1tpi2;

import static de.gebauer.cul.homematic.in.MessageInterpreter.getRSSI;
import static de.gebauer.cul.homematic.in.MessageInterpreter.toInt;
import static de.gebauer.cul.homematic.in.MessageInterpreter.toShort;

import java.math.BigDecimal;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.AckStatusEvent;
import de.gebauer.homematic.msg.Message;

public class HMLCDIM1TPI2Interpreter implements DeviceMessageInterpreter {

    // pairing:
    // [20E916->000000 #02; len=1A, flag=84, type=UNKNOWN, p=2300A44B45513031323436373320110100]
    // [13C86C->20E916 #09; len=10, flag=A0, type=CONFIG_START, p=00050000000000]
    // [20E916->13C86C #09; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->20E916 #0A; len=13, flag=A0, type=CONFIG_START, p=000802010A130BC80C6C]
    // [20E916->13C86C #0A; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->20E916 #0B; len=0B, flag=A0, type=CONFIG_START, p=0006]
    // [20E916->13C86C #0B; len=0A, flag=80, type=ACK, p=00]

    // FHEM commands:

    // pct
    // 0
    // [13C86C->20E916 #0E; len=10, flag=A0, type=COMMAND, p=0201000320FFFF]
    // [20E916->13C86C #0E; len=0E, flag=80, type=ACK, p=0101000032]
    // 4 seconds later:
    // [20E916->13C86C #0F; len=0D, flag=A4, type=SWITCH, p=06010000]
    // [13C86C->20E916 #0F; len=0A, flag=80, type=ACK, p=00]

    // 0
    // [13C86C->20E916 #10; len=10, flag=A0, type=COMMAND, p=0201000320FFFF]
    // [20E916->13C86C #10; len=0E, flag=80, type=ACK, p=0101000033]
    // 99
    // [13C86C->20E916 #11; len=10, flag=A0, type=COMMAND, p=0201C60320FFFF]
    // [20E916->13C86C #11; len=0E, flag=80, type=ACK, p=0101011033]
    // 0
    // [13C86C->20E916 #12; len=10, flag=A0, type=COMMAND, p=0201000320FFFF]
    // [20E916->13C86C #12; len=0E, flag=80, type=ACK, p=0101C52032]
    // 5 seconds later:
    // [20E916->13C86C #13; len=0D, flag=A4, type=SWITCH, p=06010000]
    // [13C86C->20E916 #13; len=0A, flag=80, type=ACK, p=00]

    // command for dimmer:
    // 02 01 00 00 00 OFF
    // 02 01 00 03 20 FFFF 0%
    // 02 01 20 03 20 FFFF 10%
    // 02 01 28 03 20 FFFF 20%
    // 02 01 64 03 20 FFFF 50%
    // 02 01 C8 03 20 FFFF 100%
    // 02 01 C8 00 00 ON

    // statusRequest
    // [13C86C->20E916 #14; len=0B, flag=A0, type=CONFIG_START, p=010E]
    // [20E916->13C86C #14; len=0E, flag=A4, type=SWITCH, p=0601000033]

    // toggle
    // [13C86C->20E916 #17; len=0F, flag=A0, type=WHATTHEHECK, p=20E916400103]
    // [20E916->13C86C #17; len=0E, flag=80, type=ACK, p=0101B72031]
    // 4 seconds later:
    // [20E916->13C86C #18; len=0D, flag=A4, type=SWITCH, p=06010000]
    // [13C86C->20E916 #18; len=0A, flag=80, type=ACK, p=00]

    // getConfig
    // [13C86C->20E916 #1E; len=10, flag=A0, type=CONFIG_START, p=00040000000000]
    // [20E916->13C86C #1E; len=16, flag=A0, type=SWITCH, p=0202010A130BC80C6C15FF1600]
    // [20E916->13C86C #1E; len=16, flag=A0, type=SWITCH, p=0202010A130BC80C6C15FF1600]
    // [20E916->13C86C #1E; len=16, flag=A0, type=SWITCH, p=0202010A130BC80C6C15FF1600]

    // getdevicepair
    // [13C86C->20E916 #21; len=0B, flag=A0, type=CONFIG_START, p=0103]
    // [20E916->13C86C #21; len=0E, flag=A0, type=SWITCH, p=0100000000]
    // [20E916->13C86C #21; len=0E, flag=A0, type=SWITCH, p=0100000000]
    // [20E916->13C86C #21; len=0E, flag=A0, type=SWITCH, p=0100000000]

    // getpair
    // [13C86C->20E916 #22; len=10, flag=A0, type=CONFIG_START, p=00040000000000]
    // [20E916->13C86C #22; len=16, flag=A0, type=SWITCH, p=0202010A130BC80C6C15FF1600]
    // [20E916->13C86C #22; len=16, flag=A0, type=SWITCH, p=0202010A130BC80C6C15FF1600]
    // [20E916->13C86C #22; len=16, flag=A0, type=SWITCH, p=0202010A130BC80C6C15FF1600]

    // off
    // [13C86C->20E916 #23; len=0E, flag=A0, type=COMMAND, p=0201000000]
    // [20E916->13C86C #23; len=0E, flag=80, type=ACK, p=0101000032]

    // on
    // [13C86C->20E916 #24; len=0E, flag=A0, type=COMMAND, p=0201C80000]
    // [20E916->13C86C #24; len=0E, flag=80, type=ACK, p=0101C80031]

    // on-for-timer
    // 5 seconds
    // [13C86C->20E916 #2A; len=10, flag=A0, type=COMMAND, p=0201C800000640]
    // [20E916->13C86C #2A; len=0E, flag=80, type=ACK, p=0101C84031]
    // 10 seconds
    // [13C86C->20E916 #26; len=10, flag=A0, type=COMMAND, p=0201C800000C80]
    // [20E916->13C86C #26; len=0E, flag=80, type=ACK, p=0101C84032]
    // 100 seconds
    // [13C86C->20E916 #2C; len=10, flag=A0, type=COMMAND, p=0201C800007D00]
    // [20E916->13C86C #2C; len=0E, flag=80, type=ACK, p=0101C84032]
    // after 10 seconds:
    // [20E916->13C86C #27; len=0D, flag=A4, type=SWITCH, p=06010000]
    // [13C86C->20E916 #27; len=0A, flag=80, type=ACK, p=00]

    // on-till
    // at 12:09: 12:11
    // [13C86C->20E916 #30; len=10, flag=A0, type=COMMAND, p=0201C800006040]
    // [20E916->13C86C #30; len=0E, flag=80, type=ACK, p=0101C84032]

    // pair
    // [13C86C->000000 #57; len=15, flag=A4, type=CONFIG_START, p=010A4B455130313234363733]
    // [20E916->13C86C #57; len=1A, flag=80, type=UNKNOWN, p=2300A44B45513031323436373320110100]
    // [20E916->13C86D #01; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->20E916 #58; len=10, flag=A0, type=CONFIG_START, p=00050000000000]
    // [20E916->13C86C #58; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->20E916 #59; len=13, flag=A0, type=CONFIG_START, p=000802010A130BC80C6C]
    // [20E916->13C86C #59; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->20E916 #5A; len=0B, flag=A0, type=CONFIG_START, p=0006]
    // [20E916->13C86C #5A; len=0A, flag=80, type=ACK, p=00]

    // regRaw
    // [13C86C->20E916 #3A; len=10, flag=A0, type=CONFIG_START, p=01050000000000]
    // [20E916->13C86C #3A; len=0A, flag=80, type=ACK, p=80]
    // [13C86C->20E916 #3B; len=0D, flag=A0, type=CONFIG_START, p=01080000]
    // [20E916->13C86C #3B; len=0A, flag=80, type=ACK, p=80]

    // sign
    // on
    // [13C86C->20E916 #3C; len=10, flag=A0, type=CONFIG_START, p=01050000000001]
    // [20E916->13C86C #3C; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->20E916 #3D; len=0D, flag=A0, type=CONFIG_START, p=01080801]
    // [20E916->13C86C #3D; len=0A, flag=80, type=ACK, p=02]
    // [13C86C->20E916 #3E; len=0B, flag=A0, type=CONFIG_START, p=0106]
    // [20E916->13C86C #3E; len=0A, flag=80, type=ACK, p=00]
    // off
    // [13C86C->20E916 #3F; len=10, flag=A0, type=CONFIG_START, p=01050000000001]
    // [20E916->13C86C #3F; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->20E916 #40; len=0D, flag=A0, type=CONFIG_START, p=01080802]
    // [20E916->13C86C #40; len=0A, flag=80, type=ACK, p=02]
    // [13C86C->20E916 #41; len=0B, flag=A0, type=CONFIG_START, p=0106]
    // [20E916->13C86C #41; len=0A, flag=80, type=ACK, p=00]

    // stop
    // [13C86C->20E916 #47; len=0B, flag=A0, type=COMMAND, p=0301]
    // [20E916->13C86C #47; len=0E, flag=80, type=ACK, p=0101000032]

    // unpair
    // [13C86C->20E916 #48; len=10, flag=A0, type=CONFIG_START, p=00050000000000]
    // [20E916->13C86C #48; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->20E916 #49; len=13, flag=A0, type=CONFIG_START, p=000802010A000B000C00]
    // [20E916->13C86C #49; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->20E916 #4A; len=0B, flag=A0, type=CONFIG_START, p=0006]
    // [20E916->13C86C #4A; len=0A, flag=80, type=ACK, p=00]

    @Override
    public Message read(RawMessage msg, AbstractDevice src, AbstractDevice dst) {
	switch (msg.getMsgType()) {
	case SWITCH:
	    // after pressing button on device:
	    // 06010000 OFF
	    // 0601C800 ON

	    // 2014.10.29 10:47:10 5: CUL dispatch A0E01800220E91613C86D 01 01 00 00 22::-60:CUL
	    // 2014.10.29 10:47:10 5: Triggering CUL_HM_HM_LC_Dim1T_Pl_2_20E916 (8 changes)
	    // 2014.10.29 10:47:10 5: Notify loop for CUL_HM_HM_LC_Dim1T_Pl_2_20E916 level: 0 %
	    // 2014.10.29 10:47:10 4: eventTypes: CUL_HM CUL_HM_HM_LC_Dim1T_Pl_2_20E916 level: 0 % -> level: .* %
	    // 2014.10.29 10:47:10 4: eventTypes: CUL_HM CUL_HM_HM_LC_Dim1T_Pl_2_20E916 deviceMsg: off (to CUL) -> deviceMsg: off (to CUL)
	    // 2014.10.29 10:47:10 4: eventTypes: CUL_HM CUL_HM_HM_LC_Dim1T_Pl_2_20E916 off -> off
	    // 2014.10.29 10:47:10 4: eventTypes: CUL_HM CUL_HM_HM_LC_Dim1T_Pl_2_20E916 running: - -> running: -
	    // 2014.10.29 10:47:10 4: eventTypes: CUL_HM CUL_HM_HM_LC_Dim1T_Pl_2_20E916 dim: stop:off -> dim: stop:off
	    // 2014.10.29 10:47:10 4: eventTypes: CUL_HM CUL_HM_HM_LC_Dim1T_Pl_2_20E916 overload: off -> overload: off
	    // 2014.10.29 10:47:10 4: eventTypes: CUL_HM CUL_HM_HM_LC_Dim1T_Pl_2_20E916 overheat: off -> overheat: off
	    // 2014.10.29 10:47:10 4: eventTypes: CUL_HM CUL_HM_HM_LC_Dim1T_Pl_2_20E916 reduced: off -> reduced: off

	    // 01 01 00 00 22

	    // push @event,"overload:".(($err&0x02)?"on":"off");
	    // push @event,"overheat:".(($err&0x04)?"on":"off");
	    // push @event,"reduced:" .(($err&0x08)?"on":"off");

	    int subType = toInt(msg.getPayload(), 0, 2);
	    short channel = toShort(msg.getPayload(), 2, 2);
	    int val = toInt(msg.getPayload(), 4, 2);
	    int err = toInt(msg.getPayload(), 6, 2);

	    boolean overload = (err >> 1 & 0x01) == 0;
	    boolean overheat = (err >> 2 & 0x01) == 0;
	    boolean reduced = (err >> 3 & 0x01) == 0;

	    // 200 is on?
	    // 0 is off

	    BigDecimal rssi = getRSSI(msg.getPayload());

	    return new DimmerStateChangeEvent(new AbstractMessageParameter(msg, src, dst, channel, rssi), new DimmerState(val, overload, overheat, reduced));

	case ACK:

	    channel = toShort(msg.getPayload(), 2, 2);

	    short success = toShort(msg.getPayload(), 6, 2);
	    rssi = getRSSI(msg.getPayload());

	    return new AckStatusEvent(msg, src, dst, channel, rssi, success != 0);
	    // flag: 80

	    // failed: bit 5 nor 6 is set (0)
	    // 0
	    // 0E09800220E91613C86D 01 01 C8 00 3C
	    // 0E10800220E91613C86D 01 01 C8 00 3A
	    // 0E10800220E91613C86D 01 01 C8 00 3A

	    // 40
	    // 0E12800220E91613C86D 01 01 28 00 3A

	    // 57
	    // 0E12800220E91613C86D 01 01 28 00 3A

	    // 100
	    // 0E0A800220E91613C86D 01 01 00 00 39
	    // 0E0A800220E91613C86D 01 01 00 00 3A
	    // 0E0B800220E91613C86D 01 01 00 00 38

	    // working: bit 5 or 6 is set
	    // 0
	    // 0E0A800220E91613C86D 01 01 C7 20 3D
	    // 0E0D800220E91613C86D 01 01 C7 20 3B
	    // 0E0F800220E91613C86D 01 01 C7 20 3A

	    // 20
	    // 0E12800220E91613C86D 01 01 4D 20 3A

	    // 39
	    // 0E11800220E91613C86D 01 01 C7 20 3C

	    // 89
	    // 0E13800220E91613C86D 01 01 29 10 39

	    // 100
	    // 0E0C800220E91613C86D 01 01 03 10 39
	    // 0E0E800220E91613C86D 01 01 01 10 3A
	    // 0E10800220E91613C86D 01 01 01 10 3A
	}

	return null;
    }

}
