package de.gebauer.homematic.hmcctc;

import static de.gebauer.cul.homematic.in.MessageInterpreter.toFloat;
import static de.gebauer.cul.homematic.in.MessageInterpreter.toInt;
import static de.gebauer.cul.homematic.in.MessageInterpreter.toShort;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.AckStatusMessage;
import de.gebauer.homematic.CommandEvent;
import de.gebauer.homematic.InfoActuatorStatus;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.StatusChangeEvent;
import de.gebauer.homematic.WeekDay;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.hmcctc.TemperaturePeriodEvent.TemperaturePeriod;
import de.gebauer.homematic.hmccvd.ClimateCommand;
import de.gebauer.homematic.hmccvd.ValveData;

public class HMCCTCInterpreter implements DeviceMessageInterpreter {

    // incoming
    // set mode
    // auto
    // [1EA808->13C86C #90; len=14, flag=A4, type=SWITCH, p=0402000000000501090000]
    // [13C86C->1EA808 #90; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #91; len=14, flag=A4, type=SWITCH, p=0402000000000501090000]
    // [13C86C->1EA808 #91; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #92; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #93; len=10, flag=A4, type=SWITCH, p=06021E00000000]
    // [13C86C->1EA808 #93; len=0A, flag=80, type=ACK, p=00]

    // auto
    // [1EA808->13C86C #97; len=14, flag=A4, type=SWITCH, p=0402000000000501090000]
    // [13C86C->1EA808 #97; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #98; len=14, flag=A4, type=SWITCH, p=0402000000000501090000]
    // [1EA808->13C86C #99; len=14, flag=A4, type=SWITCH, p=0402000000000501090000]
    // [13C86C->1EA808 #98; len=0A, flag=80, type=ACK, p=00
    // [1EA808->13C86C #9A; len=10, flag=A4, type=SWITCH, p=06021E00000000]
    // [13C86C->1EA808 #99; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #9A; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #9C; len=10, flag=A4, type=SWITCH, p=06021E00000000]
    // [13C86C->1EA808 #9C; len=0A, flag=80, type=ACK, p=00]

    // cent
    // [1EA808->13C86C #93; len=14, flag=A4, type=SWITCH, p=0402000000000501110000]
    // [13C86C->1EA808 #93; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #94; len=14, flag=A4, type=SWITCH, p=0402000000000501110000]
    // [1EA808->13C86C #95; len=14, flag=A4, type=SWITCH, p=0402000000000501110000]
    // [13C86C->1EA808 #94; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #95; len=0A, flag=80, type=ACK, p=00]

    // prog 18:00 DO 17.0°C
    // [1EA808->13C86C #94; len=14, flag=A4, type=SWITCH, p=04020000000005011B0000]
    // [13C86C->1EA808 #94; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #95; len=14, flag=A4, type=SWITCH, p=04020000000005011B0000]
    // [1EA808->13C86C #96; len=14, flag=A4, type=SWITCH, p=04020000000005011B0000]
    // [13C86C->1EA808 #95; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #97; len=14, flag=A4, type=SWITCH, p=0402000000000506220000]
    // [13C86C->1EA808 #96; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #98; len=14, flag=A4, type=SWITCH, p=0402000000000506220000]
    // [13C86C->1EA808 #97; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #98; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #9A; len=16, flag=A4, type=SWITCH, p=04020000000006611262000000]
    // [13C86C->1EA808 #99; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #9B; len=16, flag=A4, type=SWITCH, p=04020000000006611262000000]
    // [1EA808->13C86C #9C; len=16, flag=A4, type=SWITCH, p=04020000000006611262000000]
    // [13C86C->1EA808 #9C; len=0A, flag=80, type=ACK, p=00]

    // manu
    // [1EA808->13C86C #98; len=14, flag=A4, type=SWITCH, p=0402000000000501010000]
    // [13C86C->1EA808 #98; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #99; len=14, flag=A4, type=SWITCH, p=0402000000000501010000]
    // [1EA808->13C86C #9A; len=14, flag=A4, type=SWITCH, p=0402000000000501010000]
    // [13C86C->1EA808 #99; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #9B; len=10, flag=A4, type=SWITCH, p=06021D00000000]
    // [13C86C->1EA808 #9A; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #9C; len=10, flag=A4, type=SWITCH, p=06021D00000000]
    // [13C86C->1EA808 #9B; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #9C; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #9D; len=10, flag=A4, type=SWITCH, p=06021D00000000]

    // manual
    // [1EA808->13C86C #98; len=14, flag=A4, type=SWITCH, p=0402000000000501010000]
    // [13C86C->1EA808 #98; len=0A, flag=80, type=ACK, p=00]
    // [1EA808->13C86C #99; len=10, flag=A4, type=SWITCH, p=06021D00000000]
    // [13C86C->1EA808 #99; len=0A, flag=80, type=ACK, p=00]

    // FHEM commands
    // controlMode central
    // [13C86C->1EA808 #9D; len=09, flag=A1, type=COMMAND2, p=]
    // [1EA808->13C86C #9D; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #9E; len=10, flag=A0, type=CONFIG_START, p=02050000000005]
    // [1EA808->13C86C #9E; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #9F; len=0D, flag=A0, type=CONFIG_START, p=02080110]
    // [1EA808->13C86C #9F; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #A0; len=0B, flag=A0, type=CONFIG_START, p=0206]
    // [1EA808->13C86C #A0; len=0A, flag=80, type=ACK, p=00]

    // controlMode manual
    // [13C86C->1EA808 #A1; len=09, flag=A1, type=COMMAND2, p=]
    // [1EA808->13C86C #A1; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #A2; len=10, flag=A0, type=CONFIG_START, p=02050000000005]
    // [1EA808->13C86C #A2; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #A3; len=0D, flag=A0, type=CONFIG_START, p=02080100]
    // [1EA808->13C86C #A3; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #A4; len=0B, flag=A0, type=CONFIG_START, p=0206]
    // [1EA808->13C86C #A4; len=0A, flag=80, type=ACK, p=00]

    // controlMode auto
    // [13C86C->1EA808 #A5; len=09, flag=A1, type=COMMAND2, p=]
    // [1EA808->13C86C #A5; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #A6; len=10, flag=A0, type=CONFIG_START, p=02050000000005]
    // [1EA808->13C86C #A6; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #A7; len=0D, flag=A0, type=CONFIG_START, p=02080108]
    // [1EA808->13C86C #A7; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #A8; len=0B, flag=A0, type=CONFIG_START, p=0206]
    // [1EA808->13C86C #A8; len=0A, flag=80, type=ACK, p=00]

    // controlMode party
    // [13C86C->1EA808 #AC; len=09, flag=A1, type=COMMAND2, p=]
    // [1EA808->13C86C #AC; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #AD; len=10, flag=A0, type=CONFIG_START, p=02050000000005]
    // [1EA808->13C86C #AD; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #AE; len=0D, flag=A0, type=CONFIG_START, p=02080118]
    // [1EA808->13C86C #AE; len=0A, flag=80, type=ACK, p=00]
    // [13C86C->1EA808 #AF; len=0B, flag=A0, type=CONFIG_START, p=0206]
    // [1EA808->13C86C #AF; len=0A, flag=80, type=ACK, p=00]

    // 2013.12.06 20:24:07 5: [DEBUG] CUL_HM_getRxType: HASH(0x155fcf0), 12
    // 2013.12.06 20:24:07 5: [DEBUG] CUL_HM_getRxType: HASH(0x155fcf0), 12
    // Use of uninitialized value in bitwise or (|) at ./FHEM/10_CUL_HM.pm line 1932.
    // 2013.12.06 20:24:07 5: [DEBUG] CUL_HM_pushConfig: hash:HASH(0x155fcf0) src:13C86C dst:1EA808 chn:2 peerA:0 peerChn:0 list:5 content:0100
    // 2013.12.06 20:24:07 5: [DEBUG] CUL_HM_getRxType: HASH(0x155fcf0), 12
    // 2013.12.06 20:24:07 5: [DEBUG] CUL_HM_PushCmdStack: HASH(0x155fcf0) ++A00113C86C1EA80802050000000005
    // 2013.12.06 20:24:07 5: [DEBUG] CUL_HM_PushCmdStack: HASH(0x155fcf0) ++A00113C86C1EA80802080100
    // 2013.12.06 20:24:07 5: [DEBUG] CUL_HM_PushCmdStack: HASH(0x155fcf0) ++A00113C86C1EA8080206
    // 2013.12.06 20:24:07 5: [DEBUG] CUL_HM_getRxType: HASH(0x155fcf0), 12
    // 2013.12.06 20:24:07 5: [DEBUG] CUL_HM_getRxType: HASH(0x155fcf0), 12
    // 2013.12.06 20:24:09 5: COC dispatch A0C0C86701EA80800000000D838
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_Parse: iohash:HASH(0x1310e28) id:13C86C shash:HASH(0x155fcf0) dhash:HASH(0x138bee8) dname:ActionDetector
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_eventP: CUL_HM_thermostat_1EA808: ,Rcv
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_DumpProtocol: RCV L:0C N:0C F:86 CMD:70 SRC:CUL_HM_thermostat_1EA808 DST:broadcast 00D838 (WeatherEvent TEMP:0x00D8
    // HUM:0x38) (,WAKEMEUP,BCAST,RPTEN)
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_parseCommon: 0C, 70, 1EA808, 000000, 00D838
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_getRxType: HASH(0x155fcf0), 12
    // 2013.12.06 20:24:09 5: COC sending As09ACA11213C86C1EA808
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_DumpProtocol: SND L:09 N:AC F:A1 CMD:12 SRC:13C86C DST:CUL_HM_thermostat_1EA808 (HAVE_DATA) (,WAKEUP,BIDI,RPTEN)
    // 2013.12.06 20:24:09 5: COC dispatch A0AAC80021EA80813C86C00
    // Use of uninitialized value $dhash in concatenation (.) or string at ./FHEM/10_CUL_HM.pm line 311.
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_Parse: iohash:HASH(0x1310e28) id:13C86C shash:HASH(0x155fcf0) dhash: dname:COC
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_eventP: CUL_HM_thermostat_1EA808: ,Rcv
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_DumpProtocol: RCV L:0A N:AC F:80 CMD:02 SRC:CUL_HM_thermostat_1EA808 DST:13C86C 00 (ACK) (,RPTEN)
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_parseCommon: AC, 02, 1EA808, 13C86C, 00
    // 2013.12.06 20:24:09 5: COC sending As10ADA00113C86C1EA80802050000000005
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_DumpProtocol: SND L:10 N:AD F:A0 CMD:01 SRC:13C86C DST:CUL_HM_thermostat_1EA808 02050000000005 (CONFIG_START
    // CHANNEL:0x02 PEER_ADDRESS:0x000000 PEER_CHANNEL:0x00 PARAM_LIST:0x05) (,BIDI,RPTEN)
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_eventP: CUL_HM_thermostat_1EA808: ,Snd
    // 2013.12.06 20:24:09 5: COC dispatch A0AAD80021EA80813C86C00
    // Use of uninitialized value $dhash in concatenation (.) or string at ./FHEM/10_CUL_HM.pm line 311.
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_Parse: iohash:HASH(0x1310e28) id:13C86C shash:HASH(0x155fcf0) dhash: dname:COC
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_eventP: CUL_HM_thermostat_1EA808: ,Rcv
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_DumpProtocol: RCV L:0A N:AD F:80 CMD:02 SRC:CUL_HM_thermostat_1EA808 DST:13C86C 00 (ACK) (,RPTEN)
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_parseCommon: AD, 02, 1EA808, 13C86C, 00
    // 2013.12.06 20:24:09 5: COC sending As0DAEA00113C86C1EA80802080100
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_DumpProtocol: SND L:0D N:AE F:A0 CMD:01 SRC:13C86C DST:CUL_HM_thermostat_1EA808 02080100 (CONFIG_WRITE_INDEX
    // CHANNEL:0x02 DATA:0x0100) (,BIDI,RPTEN)
    // 2013.12.06 20:24:09 5: [DEBUG] CUL_HM_eventP: CUL_HM_thermostat_1EA808: ,Snd
    // 2013.12.06 20:24:10 5: COC dispatch A0AAE80021EA80813C86C00
    // Use of uninitialized value $dhash in concatenation (.) or string at ./FHEM/10_CUL_HM.pm line 311.
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_Parse: iohash:HASH(0x1310e28) id:13C86C shash:HASH(0x155fcf0) dhash: dname:COC
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_eventP: CUL_HM_thermostat_1EA808: ,Rcv
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_DumpProtocol: RCV L:0A N:AE F:80 CMD:02 SRC:CUL_HM_thermostat_1EA808 DST:13C86C 00 (ACK) (,RPTEN)
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_parseCommon: AE, 02, 1EA808, 13C86C, 00
    // 2013.12.06 20:24:10 5: COC sending As0BAFA00113C86C1EA8080206
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_DumpProtocol: SND L:0B N:AF F:A0 CMD:01 SRC:13C86C DST:CUL_HM_thermostat_1EA808 0206 (CONFIG_END CHANNEL:0x02)
    // (,BIDI,RPTEN)
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_eventP: CUL_HM_thermostat_1EA808: ,Snd
    // 2013.12.06 20:24:10 5: COC dispatch A0AAF80021EA80813C86C00
    // Use of uninitialized value $dhash in concatenation (.) or string at ./FHEM/10_CUL_HM.pm line 311.
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_Parse: iohash:HASH(0x1310e28) id:13C86C shash:HASH(0x155fcf0) dhash: dname:COC
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_eventP: CUL_HM_thermostat_1EA808: ,Rcv
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_DumpProtocol: RCV L:0A N:AF F:80 CMD:02 SRC:CUL_HM_thermostat_1EA808 DST:13C86C 00 (ACK) (,RPTEN)
    // 2013.12.06 20:24:10 5: [DEBUG] CUL_HM_parseCommon: AF, 02, 1EA808, 13C86C, 00

    // desired-temp 20
    // [13C86C->1EA808 #B0; len=09, flag=A1, type=COMMAND2, p=]
    // ??? - no response

    private static final Logger LOG = LoggerFactory.getLogger(HMCCTCInterpreter.class);

    @Override
    public Message read(RawMessage msg, AbstractDevice src, AbstractDevice dst) {
	int subType = toInt(msg.getPayload(), 0, 2);
	short channel = -1;
	if (msg.getPayload().length() >= 4) {
	    channel = toShort(msg.getPayload(), 2, 2);
	}

	switch (msg.getMsgType()) {

	case THSENSOR:
	    // no channel
	    // 0C .. 86 70 ....... ...... .... ..
	    // len cnt ty fl src dest temp humi
	    float temp = toFloat(msg.getPayload(), 0, 4, 0.1f);
	    int hum = toInt(msg.getPayload(), 4, 2);
	    return new WeatherEvent(msg, src, dst, temp, hum);

	case THERMOSTAT:
	    // flag: A1
	    // payload: 00 00

	    int vp = (int) (toFloat(msg.getPayload(), 2, 2, 100f) / 256f);
	    LOG.trace("Valve Position " + toFloat(msg.getPayload(), 2, 2, 1f) + " translated to " + vp);
	    return new ClimateCommand(msg, src, dst, subType, vp);

	case SWITCH:

	    if (subType == 4) {
		// 0403 167DE9 01 05 05 16 0000 windowopen-temp chan 03, dev
		// 167DE9
		// on slot 01
		Matcher matcher;
		if ((matcher = matcherFor(msg, "^0403(......)(..)0505(..)0000$")).matches()) {
		    String tDev = matcher.group(1);
		    LOG.warn("'^0403(......)(..)0505(..)0000$' Not yet supported");
		}

		// 1A 0C A4 10 1EA808 13C86C 04020000000005 0B3C0C1E0D900E280000
		// payload: 04 02 00 00 00 00 05 0B 3C 0C 1E 0D 90 0E 28 00 00
		// relevant: 5 0B 3C 0C 1E 0D 90 0E 28 00 00
		if ((matcher = matcherFor(msg, "^0402000000000(.)(..)(..)(..)(..)(..)(..)(..)(..)(.*)$")).matches()) {
		    int pList = toInt(matcher.group(1));
		    int o1 = toInt(matcher.group(2));
		    int v1 = toInt(matcher.group(3));
		    int o2 = toInt(matcher.group(4));
		    int v2 = toInt(matcher.group(5));
		    int o3 = toInt(matcher.group(6));
		    int v3 = toInt(matcher.group(7));
		    int o4 = toInt(matcher.group(8));
		    int v4 = toInt(matcher.group(9));

		    int dayOff;
		    int maxDays;
		    int basevalue;

		    int[] days = new int[] { Calendar.SATURDAY, Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY,
			    Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY };

		    if (pList == 5 || pList == 6) {
			if (pList == 5) {
			    dayOff = 0;
			    maxDays = 5;
			    basevalue = 0x0B;
			} else {
			    dayOff = 5;
			    maxDays = 2;
			    basevalue = 0x01;
			}
			int idx = o1 - basevalue;
			int dayIdx = idx / 48;
			if (idx % 4 == 0 && dayIdx < maxDays) {
			    idx -= 48 * dayIdx;
			    idx /= 2;

			    TemperaturePeriod upTo = new TemperaturePeriod(v1 / 6, (v1 % 6) * 10, v2 / 2);
			    TemperaturePeriod upTo2 = new TemperaturePeriod(v3 / 6, (v3 % 6) * 10, v4 / 2);

			    return new TemperaturePeriodEvent(msg, src, dst, (short) 2, dayIdx + dayOff, upTo, upTo2);

			    // data reg
			    // 0B3C0C1E0D900E280000 05
			    // 0: [10:0, temp=15] [24:0, temp=20]
			    // 3B3C3C1E3D903E280000 05
			    // 0: [10:0, temp=15] [24:0, temp=20]
			    // 6B336C1E6D396E280000 05
			    // 0: [8:30, temp=15] [9:30, temp=20]
			    // 9B339C1E9D399E280000 05
			    // 0: [8:30, temp=15] [9:30, temp=20]
			    // CB33CC1ECD39CE280000 05
			    // 0: [8:30, temp=15] [9:30, temp=20]
			    // 0133021E033904280000 06
			    // 0: [8:30, temp=15] [9:30, temp=20]
			    // 3133321E333934280000 06
			    // 0: [8:30, temp=15] [9:30, temp=20]
			    // 0F8A102A119012220000 05
			    // 2: [23:0, temp=21] [24:0, temp=17]
			    // 3F8A402A419042220000 05
			    // 2: [23:0, temp=21] [24:0, temp=17]
			    // 6F72701E719072280000 05
			    // 2: [19:0, temp=15] [24:0, temp=20]
			    // 9F72A01EA190A2280000 05
			    // 2: [19:0, temp=15] [24:0, temp=20]
			    // CF72D01ED190D2280000 05
			    // 2: [19:0, temp=15] [24:0, temp=20]
			    // 0572061E079008280000 06
			    // 2: [19:0, temp=15] [24:0, temp=20]
			    // 3572361E379038280000 06
			    // 2: [19:0, temp=15] [24:0, temp=20]

			    // TODO set the periods on the device

			}

			for (int day : days) {
			    int twentyfour = 0;
			    String m = "tempList" + day + ":";
			    for (int i = 0; i < 24; i++) {

			    }
			}

		    }
		} else if ((matcher = matcherFor(msg, "^04020000000005(..)(..)(....)$")).matches()) {
		    // when mode is set on device:
		    // 0402000000000501090000
		    int o1 = toInt(matcher.group(1));
		    int v1 = toInt(matcher.group(2));

		    if (o1 == 1) {
			DisplayMode dm = (v1 & 0x01) == 1 ? DisplayMode.TEMPERATURE_AND_HUMDITY : DisplayMode.TEMPERATURE;
			DisplayTemp dt = (v1 & 0x02 >> 1) == 1 ? DisplayTemp.SETPOINT : DisplayTemp.ACTUAL;
			TemperatureUnit tempUnit = (v1 & 0x04 >> 2) == 1 ? TemperatureUnit.FAHRENHEIT : TemperatureUnit.CELSIUS;
			// 0X00 00000 is Manual
			// 0x08 01000 is Auto
			// 0X10 10000 is Central
			// 0x18 11000 is Party
			ControlMode ctrlMode = ControlMode.valueOf(v1 & 0x08 >> 3);
			// starts with sunday?
			// bit mask: 11100000
			WeekDay weekDay = WeekDay.of((v1 & 0xE0) >> 5);
			return new BasicTCInfo(msg, src, dst, channel, dm, dt, tempUnit, ctrlMode, weekDay);
		    } else if (o1 == 2) {

			LOG.warn(o1 + ":" + v1);
		    } else {
			// eg ... 06 22 .. ..
			LOG.warn("Unhandled {}" + msg);

		    }

		}

	    } else if (subType == 6) {
		// channel 2
		// 06 02 2C 000000 00
		// strip sType and chnl
		String data = msg.getPayload().substring(4);

		double desiredTemp = (double) toShort(data, 0, 2) / 2.0;
		LOG.info("Desired Temp: " + desiredTemp);

		StatusChangeEvent.ChannelStatus chStatus = new StatusChangeEvent.ChannelStatus();
		chStatus.channel = toShort(msg.getPayload(), 2, 2);
		chStatus.peerId = data.substring(2, 6);
		chStatus.peerChannel = toShort(data.substring(2, 6));

		return new TemperaturStatus(msg, src, dst, desiredTemp, chStatus);
	    }
	    break;
	case COMMAND:

	    // MessageType.
	    LOG.info(msg.getPayload());

	    int factor = toInt(msg.getPayload(), 4, 2) / 2;
	    LOG.info("{}%", factor);
	    if (msg.getPayload().length() == 14 && "FFFF".equals(msg.getPayload().substring(10, 14))) {
		LOG.warn("Could not interpret whole message {}", msg.getPayload());
	    }
	    return new CommandEvent(msg, src, dst, channel, msg.getPayload());

	case CONFIG:
	    // 0F 15 A0 01 1EA808 1C4E7F 01 08 09 05 0A 00
	    Matcher matcher = Pattern.compile("^010809(..)0A(..)$").matcher(msg.getPayload());
	    if (matcher.matches()) {
		short offset = toShort(matcher.group(1));
		short vep = toShort(matcher.group(2));

		ValveData vdValveData = new ValveData();
		vdValveData.setErrorPosition(vep);
		vdValveData.setOffset(offset);

		return new AckStatusMessage(msg, src, dst, (short) 8, vdValveData);
	    }

	    matcher = Pattern.compile("^0105(......)0105$").matcher(msg.getPayload());
	    if (matcher.matches()) {
		// TODO interpret payload

		// return new ConfigStartCommand(msg, src, dst, (short) 5, (short) -1);
	    }
	    // 10 14 A0 01 1EA808 1C4E7F 01 05 1EA808 01 05
	    // payload: 0105 1EA808 0105
	    // payload: 0105 1EA808 0105

	    // get ready for config
	    // return
	    // return new ConfigStartCommand(msg, src, dst);
	}

	// TODO interpret ACK
	return null;
    }

    private Matcher matcherFor(RawMessage msg, String regex) {
	return Pattern.compile(regex).matcher(msg.getPayload());
    }

    @Override
    public Model getModel() {
	return Model.HMCCTC;
    }
}
