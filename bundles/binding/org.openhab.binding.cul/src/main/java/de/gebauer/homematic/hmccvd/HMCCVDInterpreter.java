package de.gebauer.homematic.hmccvd;

import static de.gebauer.cul.homematic.in.MessageInterpreter.toInt;
import static de.gebauer.cul.homematic.in.MessageInterpreter.toShort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.hmccvd.ValveData.MotorError;
import de.gebauer.homematic.msg.AckStatusEvent;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageType;

public class HMCCVDInterpreter implements DeviceMessageInterpreter {

    // Pairing HMCCTC with HMCCVD
    // 1A A0 84 00 1EA808 000000 21 00394A45513037303939323258 00 FFFF
    // 1A 8A 84 00 1C4E7F 000000 20 003A4A45513033313237323158 01 0100
    // 1A A1 A0 00 1EA808 1C4E7F 21 00394A45513037303939323258 00 0200
    // 0A A1 80 02 1C4E7F 1EA808 00
    // 10 A2 A0 01 1EA808 1C4E7F 01 040000000005
    // 10 A2 80 10 1C4E7F 1EA808 02 09000A040000

    // 1A A2 84 00 1EA808 000000 21 00394A45513037303939323258 00 FFFF
    // 1A 0C 84 00 1C475A 000000 20 003A4A45513033313333373258 01 0100
    // 1A A3 A0 00 1EA808 1C475A 21 00394A45513037303939323258 00 0200
    // 0A A3 80 02 1C475A 1EA808 00
    // 10 A4 A0 01 1EA808 1C475A 01 040000000005
    // 10 A4 80 10 1C475A 1EA808 02 09000A0F0000

    // 1 device already connected (1C4E7F)
    // 17:54:56.593 [1EA808->000000 #47; len=1A, flag=VAL_84, type=UNKNOWN, p=21 0039 4A4551303730393932325800 FF FF]
    // 17:55:01.952 [1C475A->000000 #15; len=1A, flag=VAL_84, type=UNKNOWN, p=20 003A 4A4551303331333337325801 01 00]
    // 17:55:02.082 [1EA808->1C475A #48; len=1A, flag=VAL_A0, type=UNKNOWN, p=21 0039 4A4551303730393932325800 02 00]
    // 17:55:02.193 [1C475A->1EA808 #48; len=0A, flag=VAL_80, type=ACK, p=00]
    // 17:55:02.383 [1EA808->1C475A #49; len=10, flag=VAL_A0, type=CONFIG, p=01040000000005] ConfigRegisterReadCommand
    // 17:55:02.511 [1C475A->1EA808 #49; len=10, flag=VAL_80, type=SWITCH, p=0209000A0F0000]
    // 17:55:25.842 [1EA808->1C4E7F #47; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 17:55:25.974 [1C4E7F->1EA808 #47; len=0E, flag=VAL_82, type=ACK, p=0101000031]
    // 17:58:08.596 [1EA808->000000 #48; len=0C, flag=VAL_86, type=THSENSOR, p=00E840]
    // 17:58:28.598 [1EA808->1C475A #48; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 3:26 206
    // 17:58:28.726 [1C475A->1EA808 #48; len=0E, flag=null, type=ACK, p=0101000037] [motor: OK STOP 0% errPos:0% off:0% bat:OK]
    // 17:58:29.096 [1C475A->1EA808 #4A; len=16, flag=VAL_A4, type=SWITCH, p=0400000000000509000A0F0000] ParamChangeMessage
    // 17:58:29.214 [1EA808->1C475A #4A; len=0A, flag=VAL_80, type=ACK, p=00]
    // 18:00:56.847 [1EA808->000000 #49; len=0C, flag=VAL_86, type=THSENSOR, p=00E93F]
    // 18:01:16.847 [1EA808->1C4E7F #49; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 5:50 350
    // 18:01:16.978 [1C4E7F->1EA808 #49; len=0E, flag=VAL_82, type=ACK, p=010100003F]
    // 18:03:30.601 [1EA808->000000 #4A; len=0C, flag=VAL_86, type=THSENSOR, p=00E93F]
    // 18:03:50.602 [1EA808->1C475A #4A; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 5:30 330
    // 18:03:50.730 [1C475A->1EA808 #4A; len=0E, flag=VAL_82, type=ACK, p=010100003E]
    // 18:05:50.103 [1EA808->000000 #4B; len=0C, flag=VAL_86, type=THSENSOR, p=00E93F]
    // 18:06:10.104 [1EA808->1C4E7F #4B; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 18:06:10.236 [1C4E7F->1EA808 #4B; len=0E, flag=VAL_82, type=ACK, p=0101000033]
    // 18:07:55.105 [1EA808->000000 #4C; len=0C, flag=VAL_86, type=THSENSOR, p=00E93F]
    // 18:08:15.105 [1EA808->1C475A #4C; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 4:25 265
    // 18:08:15.235 [1C475A->1EA808 #4C; len=0E, flag=VAL_82, type=ACK, p=0101000040]
    // 18:10:49.609 [1EA808->000000 #4D; len=0C, flag=VAL_86, type=THSENSOR, p=00EA3F]
    // 18:11:09.608 [1EA808->1C4E7F #4D; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 18:11:09.740 [1C4E7F->1EA808 #4D; len=0E, flag=VAL_82, type=ACK, p=0101000032]
    // 18:13:29.862 [1EA808->000000 #4E; len=0C, flag=VAL_86, type=THSENSOR, p=00EA3F]
    // 18:13:49.863 [1EA808->1C475A #4E; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 5:35 335
    // 18:13:49.992 [1C475A->1EA808 #4E; len=0E, flag=VAL_82, type=ACK, p=010100003B]
    // 18:15:55.615 [1EA808->000000 #4F; len=0C, flag=VAL_86, type=THSENSOR, p=00EA3F]
    // 18:16:15.613 [1EA808->1C4E7F #4F; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 18:16:15.744 [1C4E7F->1EA808 #4F; len=0E, flag=VAL_82, type=ACK, p=0101000032]
    // 18:18:06.867 [1EA808->000000 #50; len=0C, flag=VAL_86, type=THSENSOR, p=00EA3F]
    // 18:18:26.864 [1EA808->1C475A #50; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 4:35 275
    // 18:18:26.995 [1C475A->1EA808 #50; len=0E, flag=VAL_82, type=ACK, p=010100003C]
    // 18:21:07.870 [1EA808->000000 #51; len=0C, flag=VAL_86, type=THSENSOR, p=00EA3F]
    // 18:21:27.869 [1EA808->1C4E7F #51; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 18:21:27.999 [1C4E7F->1EA808 #51; len=0E, flag=VAL_82, type=ACK, p=0101000033]
    // 18:23:54.373 [1EA808->000000 #52; len=0C, flag=VAL_86, type=THSENSOR, p=00EA3F]
    // 18:24:14.372 [1EA808->1C475A #52; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 5:50 350
    // 18:24:14.503 [1C475A->1EA808 #52; len=0E, flag=VAL_82, type=ACK, p=0101000038]
    // 18:26:26.375 [1EA808->000000 #53; len=0C, flag=VAL_86, type=THSENSOR, p=00EA3F]
    // 18:26:46.375 [1EA808->1C4E7F #53; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 18:26:46.509 [1C4E7F->1EA808 #53; len=0E, flag=VAL_82, type=ACK, p=0101000039]
    // 18:28:44.128 [1EA808->000000 #54; len=0C, flag=VAL_86, type=THSENSOR, p=00EA3F]
    // 18:29:04.127 [1EA808->1C475A #54; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 4:50 290
    // 18:29:04.259 [1C475A->1EA808 #54; len=0E, flag=VAL_82, type=ACK, p=0101000039]
    // 18:30:47.382 [1EA808->000000 #55; len=0C, flag=VAL_86, type=THSENSOR, p=00EB3F]
    // 18:31:07.379 [1EA808->1C4E7F #55; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 18:31:07.508 [1C4E7F->1EA808 #55; len=0E, flag=VAL_82, type=ACK, p=010100003B]
    // 18:33:40.133 [1EA808->000000 #56; len=0C, flag=VAL_86, type=THSENSOR, p=00EA3F]
    // 18:34:00.137 [1EA808->1C475A #56; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 4:55 295
    // 18:34:00.261 [1C475A->1EA808 #56; len=0E, flag=VAL_82, type=ACK, p=010100003A]
    // 18:36:18.385 [1EA808->000000 #57; len=0C, flag=VAL_86, type=THSENSOR, p=00EB3F]
    // 18:36:38.384 [1EA808->1C4E7F #57; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 18:36:38.516 [1C4E7F->1EA808 #57; len=0E, flag=VAL_82, type=ACK, p=010100003A]
    // 18:40:14.681 [1EA808->000000 #58; len=0C, flag=VAL_86, type=THSENSOR, p=00EB3F]
    // 18:40:14.682 [1EA808->1C475A #58; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 4:14 254
    // 18:40:14.683 [1C475A->1EA808 #58; len=0E, flag=VAL_82, type=ACK, p=0101000038]
    // 18:40:51.891 [1EA808->000000 #59; len=0C, flag=VAL_86, type=THSENSOR, p=00EB3F]
    // 18:41:11.890 [1EA808->1C4E7F #59; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 18:41:12.020 [1C4E7F->1EA808 #59; len=0E, flag=VAL_82, type=ACK, p=010100003C]
    // 18:43:50.893 [1EA808->000000 #5A; len=0C, flag=VAL_86, type=THSENSOR, p=00EB3E]
    // 18:44:10.892 [1EA808->1C475A #5A; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000] 3:56 236
    // 18:44:11.025 [1C475A->1EA808 #5A; len=0E, flag=VAL_82, type=ACK, p=010100003A]
    // 18:46:35.646 [1EA808->000000 #5B; len=0C, flag=VAL_86, type=THSENSOR, p=00EB3E]
    // 18:46:55.645 [1EA808->1C4E7F #5B; len=0B, flag=VAL_A2, type=THERMOSTAT, p=0000]
    // 18:46:55.776 [1C4E7F->1EA808 #5B; len=0E, flag=VAL_82, type=ACK, p=010100003A]

    // 03:58:48.941 [1EA808->000000 #35; len=1A, flag=VAL_84, type=UNKNOWN, p=2100394A4551303730393932325800FFFF]
    // 03:58:55.191 [1C4E7F->000000 #38; len=1A, flag=VAL_84, type=UNKNOWN, p=20003A4A45513033313237323158010100]
    // 03:58:55.321 [1EA808->1C4E7F #36; len=1A, flag=VAL_A0, type=UNKNOWN, p=2100394A45513037303939323258000200]
    // 03:58:55.432 [1C4E7F->1EA808 #36; len=0A, flag=VAL_80, type=ACK, p=00]
    // 03:58:55.620 [1EA808->1C4E7F #37; len=10, flag=VAL_A0, type=CONFIG, p=01040000000005]
    // 03:58:55.747 [1C4E7F->1EA808 #37; len=10, flag=VAL_80, type=SWITCH, p=0209000A040000]
    //

    // ?
    // 0D 00 A4 10 1C475A 13C86D 06000000

    // first messages:
    // 22:42:59.980 [206185->000000 #02; len=1A, flag=VAL_84, type=UNKNOWN, p=2100394B4551303033393635315800FFFF2E] 14153 -28803
    // 22:43:00.105 [1C475A->000000 #CA; len=1A, flag=VAL_84, type=UNKNOWN, p=20003A4A455130333133333732580101002E] 9885 -37341
    // 22:43:02.555 [206185->000000 #03; len=10, flag=VAL_A0, type=CONFIG, p=010400000000052D] 2575 -11579
    // 22:43:25.190 [206185->000000 #02; len=1A, flag=VAL_84, type=UNKNOWN, p=2100394B4551303033393635315800FFFF23] 25209 11055
    // 22:43:29.539 [1C475A->000000 #CB; len=1A, flag=VAL_84, type=UNKNOWN, p=20003A4A4551303331333337325801010035] 29434 19549
    // 22:43:29.671 [206185->1C475A #03; len=1A, flag=VAL_A0, type=UNKNOWN, p=2100394B4551303033393635315800020023] -1 -1
    // 22:43:29.783 [1C475A->206185 #03; len=0A, flag=VAL_80, type=ACK, p=0035] -1 -1
    // 22:43:29.977 [206185->1C475A #04; len=10, flag=VAL_A0, type=CONFIG, p=0104000000000524] 0 -1
    // 22:43:30.103 [1C475A->206185 #04; len=10, flag=VAL_80, type=SWITCH, p=0209000A0F000035] -1 -1
    // 22:45:32.658 [206185->000000 #02; len=0C, flag=VAL_86, type=THSENSOR, p=00CD3B09] 127467 102257
    // 22:45:52.657 [206185->1C475A #02; len=0B, flag=VAL_A2, type=THERMOSTAT, p=00000C] 142 -1
    // 22:45:52.788 [1C475A->206185 #02; len=0E, flag=VAL_82, type=ACK, p=01011E002B19] -1 -1
    // 22:48:10.662 [206185->000000 #03; len=0C, flag=VAL_86, type=THSENSOR, p=00D62F14] 158004 30536
    // 22:48:30.661 [206185->1C475A #03; len=0B, flag=VAL_A2, type=THERMOSTAT, p=000008] 158 -1
    // 22:48:30.793 [1C475A->206185 #03; len=0E, flag=VAL_82, type=ACK, p=010100002B12] 158 -1

    // 22:43:30.108 [1C475A->206185 #04; len=10, flag=VAL_80, type=SWITCH, p=0209000A0F000035]
    // 22:45:52.658 (Bedroom_Temperature->Bedroom_Heating #02) [command=0, valvePos=0]
    // ..:02:22:500 142550 143500
    // 22:48:30.662 (Bedroom_Temperature->Bedroom_Heating #03) [command=0, valvePos=0]
    // ..:02:38.004 158000

    // waren schon gepairt (kein reset)
    // 01:17:34.311 [206185->1C475A #3E; len=0B, flag=VAL_A2, type=THERMOSTAT, p=00000D] 139 -1
    // 01:17:34.442 [1C475A->206185 #3E; len=0E, flag=VAL_82, type=ACK, p=010100002B1B] 139 -1
    // 01:18:30.923 [206185->000000 #3F; len=1A, flag=VAL_84, type=UNKNOWN, p=2100394B4551303033393635315800FFFFFC] 76611 -62391
    // 01:18:35.249 [1C475A->000000 #3F; len=1A, flag=VAL_84, type=UNKNOWN, p=20003A4A4551303331333337325801010016] 9305710 9276276
    // 01:18:35.379 [206185->1C475A #40; len=1A, flag=VAL_A0, type=UNKNOWN, p=2100394B4551303033393635315800020002] 61 -1
    // 01:18:35.492 [1C475A->206185 #40; len=0A, flag=VAL_80, type=ACK, p=0016] 61 -1
    // 01:18:35.663 [206185->1C475A #41; len=10, flag=VAL_A0, type=CONFIG, p=0104000000000502] 0 -1
    // 01:18:35.789 [1C475A->206185 #41; len=10, flag=VAL_80, type=SWITCH, p=0209000A0F000016] 61 -1
    // 01:19:18.815 [206185->000000 #3F; len=0C, flag=VAL_86, type=THSENSOR, p=00B92B1A] 47891 -28721
    // 01:19:38.814 [206185->1C475A #3F; len=0B, flag=VAL_A2, type=THERMOSTAT, p=00001B] 63 -1
    // 01:19:38.945 [1C475A->206185 #3F; len=0E, flag=VAL_82, type=ACK, p=010100002E19] 124 -1
    // 01:22:13.072 [206185->000000 #40; len=0C, flag=VAL_86, type=THSENSOR, p=00BA2B1A] 174257 126366
    // 01:22:33.068 [206185->1C475A #40; len=0B, flag=VAL_A2, type=THERMOSTAT, p=00001B] 174 -1
    // 01:22:33.201 [1C475A->206185 #40; len=0E, flag=VAL_82, type=ACK, p=010100002E18] 174 -1

    // waren schon gepairt (mit reset)
    // 01:52:29.868 [206185->1C475A #4C; len=0B, flag=VAL_A2, type=THERMOSTAT, p=00001D] 129 -1
    // 01:52:29.999 [1C475A->206185 #4C; len=0E, flag=VAL_82, type=ACK, p=010100002D26] 129 -1
    // 01:55:08.623 [206185->000000 #4D; len=0C, flag=VAL_86, type=THSENSOR, p=00B82A20] 178754 49750
    // 01:55:28.623 [206185->1C475A #4D; len=0B, flag=VAL_A2, type=THERMOSTAT, p=000020] 178 -1
    // 01:57:52.878 [206185->000000 #4E; len=0C, flag=VAL_86, type=THSENSOR, p=00B82A1D] 164253 -14502
    // 01:58:38.261 [206185->000000 #00; len=0D, flag=VAL_84, type=SWITCH, p=0600000002] 45383 -118871
    // 01:58:54.350 [206185->000000 #02; len=1A, flag=VAL_84, type=UNKNOWN, p=2100394B4551303033393635315800FFFF0B] 16088 -29295
    // 01:58:59.474 [1C475A->000000 #4E; len=1A, flag=VAL_84, type=UNKNOWN, p=20003A4A4551303331333337325801010021] 2424225 -6881485
    // 01:58:59.606 [206185->1C475A #03; len=1A, flag=VAL_A0, type=UNKNOWN, p=2100394B455130303339363531580002000B] 210 -1
    // 01:58:59.718 [1C475A->206185 #03; len=0A, flag=VAL_80, type=ACK, p=0021] 389 -1
    // 01:58:59.912 [206185->1C475A #04; len=10, flag=VAL_A0, type=CONFIG, p=010400000000050A] 0 -1
    // 01:59:00.037 [1C475A->206185 #04; len=10, flag=VAL_80, type=SWITCH, p=0209000A0F000021] 390 -1
    // 02:01:30.763 [206185->000000 #02; len=0C, flag=VAL_86, type=THSENSOR, p=00BE2D19] 156413 140324
    // 02:01:50.764 [206185->1C475A #02; len=0B, flag=VAL_A2, type=THERMOSTAT, p=035A1F] 171 -1
    // 02:01:50.896 [1C475A->206185 #02; len=0E, flag=VAL_82, type=ACK, p=010100102B2A] 560 -1
    // 02:04:08.768 [206185->000000 #03; len=0C, flag=VAL_86, type=THSENSOR, p=00BF2B1C] 158004 1590
    // 02:04:28.768 [206185->1C475A #03; len=0B, flag=VAL_A2, type=THERMOSTAT, p=005A1C] 158 -1
    // 02:04:28.900 [1C475A->206185 #03; len=0E, flag=VAL_82, type=ACK, p=010146002A28] 158 -1

    // 00:20:08.333 [1EA808->000000 #03; len=0C, flag=VAL_86, type=THSENSOR, p=00F73264] 154002 -14251
    // 00:22:27.838 [1EA808->000000 #04; len=0C, flag=VAL_86, type=THSENSOR, p=00FA3064] 139504 -14499
    // 00:23:24.528 [1EA808->000000 #05; len=1A, flag=VAL_84, type=UNKNOWN, p=2100394A4551303730393932325800FFFF10] 56690 -82815
    // 00:23:38.284 [1C4E7F->000000 #32; len=1A, flag=VAL_84, type=UNKNOWN, p=20003A4A4551303331323732315801010033] 666013 659734
    // 00:23:38.413 [1EA808->1C4E7F #06; len=1A, flag=VAL_A0, type=UNKNOWN, p=2100394A4551303730393932325800020006] 665 -1
    // 00:23:38.525 [1C4E7F->1EA808 #06; len=0A, flag=VAL_80, type=ACK, p=002F] -1 -1
    // 00:23:38.715 [1EA808->1C4E7F #07; len=10, flag=VAL_A0, type=CONFIG, p=0104000000000501] 0 -1
    // 00:23:38.841 [1C4E7F->1EA808 #07; len=10, flag=VAL_80, type=SWITCH, p=0209000A0F00002F] 0 -1
    // 00:24:32.841 [1EA808->000000 #05; len=0C, flag=VAL_86, type=THSENSOR, p=00FA2F42] 68313 11623
    // 00:24:52.841 [1EA808->1C4E7F #05; len=0B, flag=VAL_A2, type=THERMOSTAT, p=000042] 74 -1
    // 00:24:52.973 [1C4E7F->1EA808 #05; len=0E, flag=VAL_82, type=ACK, p=010100002B33] 74 -1

    private static final Logger LOG = LoggerFactory.getLogger(HMCCVDInterpreter.class);

    @Override
    public Message read(RawMessage msg, AbstractDevice src, AbstractDevice dst) {
	int subType = toInt(msg.getPayload(), 0, 2);
	if (MessageType.ACK == msg.getMsgType()) {
	    // ACK_STATUS CHANNEL:0x01 STATUS:0x00 UP:0x00 DOWN:0x00 LOWBAT:0x00
	    // RSSI:0x31) (,WAKEMEUP,RPTEN)

	    // 01 01 00 00 3332
	    // 01 01 00 00 2B29
	    // 01 01 00 00 3335

	    Pattern ptrn = Pattern.compile("^(..)(..)(..)(..)(..)(..)*$");
	    Matcher matcher = ptrn.matcher(msg.getPayload());
	    if (matcher.matches()) {
		// sub type is always 01???
		short chnl = toShort(matcher.group(2));
		short vp = (short) (toShort(matcher.group(3)) / 2);
		short err = toShort(matcher.group(4));

		short rssi;
		if (matcher.groupCount() == 5) {
		    rssi = toShort(matcher.group(5));
		} else if (matcher.groupCount() == 6) {
		    LOG.trace("Uninterpreted: " + matcher.group(5));
		    rssi = toShort(matcher.group(6));
		} else {
		    LOG.error("Could not interpret ACK message from Valve Device " + msg);
		    return null;
		}

		int cmpVal = 0xFF;
		cmpVal = (cmpVal ^ err) | err;

		int stErr = (err >> 1) & 0x7;

		ValveData valveData = new ValveData();
		valveData.setPosition(vp);

		if (stErr == 0) {
		    valveData.setMotorError(MotorError.OK);
		    valveData.setBatteryStatus(BatteryStatus.OK);
		} else {
		    if (stErr == 1) {
			valveData.setMotorError(MotorError.BLOCKED);
			valveData.setBatteryStatus(BatteryStatus.OK);
		    } else if (stErr == 2) {
			valveData.setMotorError(MotorError.LOOSE);
			valveData.setBatteryStatus(BatteryStatus.OK);
		    } else if (stErr == 3) {
			valveData.setMotorError(MotorError.ADJUSTING_RANGE_TO_SMALL);
			valveData.setBatteryStatus(BatteryStatus.OK);
		    } else if (stErr == 4) {
			valveData.setBatteryStatus(BatteryStatus.LOW);
			valveData.setMotorError(MotorError.OK);
		    }
		}

		if ((err & 0x30) == 0x00) {
		    valveData.setMotorState(ValveData.MotorState.STOP);
		} else if ((err & 0x30) == 0x10) {
		    valveData.setMotorState(ValveData.MotorState.OPENING);
		} else if ((err & 0x30) == 0x20) {
		    valveData.setMotorState(ValveData.MotorState.CLOSING);
		}

		return new AckStatusEvent(msg, src, dst, chnl, rssi, valveData);
	    }
	} else if (MessageType.SWITCH == msg.getMsgType()) {
	    // CMD:A010 SRC:13F251 DST:5D24C9 0401 00000000 05 09:00
	    // 0A:07 00:00
	    // status change report to paired central unit
	    // read List5 reg 09 (offset) and 0A (err-pos)
	    // list 5 is channel-dependant not link dependant
	    // => Link discriminator (00000000) is fixed

	    Pattern compile = Pattern.compile("^0401000000000509(..)0A(..)");
	    Matcher matcher2 = compile.matcher(msg.getPayload());

	    if (matcher2.matches()) {
		short off = toShort(matcher2.group(1));
		short vep = toShort(matcher2.group(2));

		ValveData valveData = new ValveData();
		valveData.setOffset(off);
		valveData.setErrorPosition(vep);

		new AckStatusEvent(msg, src, dst, (short) -1, (short) -1, valveData);
	    }

	    // when device is started we get the following message:
	    // RawMessage [1C475A->13C86C#00; len=0D, flag=A4, type=10, p=06000000]

	    // param response is parsed by MessageInterpreter
	    // [1C475A->1EA808 #49; len=10, flag=VAL_80, type=SWITCH, p=0209000A0F0000]

	}
	return null;
    }
}
