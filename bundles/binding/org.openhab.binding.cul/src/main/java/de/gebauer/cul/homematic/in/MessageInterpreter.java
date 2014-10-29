package de.gebauer.cul.homematic.in;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.Utils;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Channelable;
import de.gebauer.homematic.device.DeviceFactory;
import de.gebauer.homematic.device.DeviceStore;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.msg.AbstractMessage;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.AckStatusEvent;
import de.gebauer.homematic.msg.ConfigPeerListMessage;
import de.gebauer.homematic.msg.ConfigRegisterReadMessage;
import de.gebauer.homematic.msg.DeviceInfoEvent;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageType;
import de.gebauer.homematic.msg.ParamChangeMessage;
import de.gebauer.homematic.msg.ParamResponseMessage;
import de.gebauer.homematic.msg.StatusChangeEvent;

public class MessageInterpreter implements MessageParser, DeviceMessageInterpreter {

    private static final BigDecimal ONE_HALF = BigDecimal.ONE.divide(new BigDecimal(2));
    private static final Logger LOG = LoggerFactory.getLogger(MessageInterpreter.class);
    private static final Logger MESSAGES = LoggerFactory.getLogger("MESSAGES");

    private final DeviceStore deviceStore;

    public MessageInterpreter(final DeviceStore deviceStore) {
	this.deviceStore = deviceStore;
    }

    @Override
    public Message parse(final String readLine) {

	final RawMessage msg = getRawMessage(readLine);

	AbstractDevice src = this.deviceStore.get(msg.getSrc());
	final AbstractDevice dst = this.deviceStore.get(msg.getDst());

	return this.read(msg, src, dst);
    }

    @Override
    public Message read(RawMessage msg, AbstractDevice src, AbstractDevice dst) {
	try {
	    debug(msg, src, dst);
	} catch (Exception e) {
	}
	if (MessageType.UNKNOWN == msg.getMsgType()) {
	    // add unknown device;
	    src = this.infoUpdtDevData(msg.getSrc(), msg.getPayload());
	    // 20003A4A45513033313333373258010100
	    final String peerChA = msg.getPayload().substring(28, 30);
	    final String peerChB = msg.getPayload().substring(30, 32);
	    final String cmd = msg.getPayload().substring(32);

	    return new DeviceInfoEvent(msg, src, dst, src.getInfo(), toShort(peerChA), toShort(peerChB), cmd);
	}

	if (src == null) {
	    LOG.info("Unknown device " + msg.getSrc());
	    return null;
	}

	final DeviceMessageInterpreter interpreter = src.getInterpreter();
	if (interpreter != null) {
	    Message intrprMsg = interpreter.read(msg, src, dst);
	    if (intrprMsg == null) {
		// return a commonly parsed message
		intrprMsg = this.parseCommon(msg, src, dst);
	    }

	    if (intrprMsg != null) {
		return intrprMsg;
	    }
	}
	LOG.warn("Could not interpret {}", msg);

	return null;
    }

    private void debug(RawMessage msg, AbstractDevice src, AbstractDevice dst) {
	long diff = -1;
	long delta = -1;
	if (dst != null) {
	    Message lastEventReceived = dst.getLastEventReceived();
	    if (lastEventReceived != null) {
		Calendar lastMsgTs = lastEventReceived.getTimestamp();
		Calendar now = Calendar.getInstance();
		long diffMillis = now.getTime().getTime() - lastMsgTs.getTime().getTime();
		diff = diffMillis / 1000;
	    }
	}
	if (src != null) {
	    if (msg.getDst().equals(AbstractMessage.BROAD_CAST_ADDRESS)) {
		Iterator<Message> eventsSent = src.getEventsSent();
		Message last = null;
		while (eventsSent.hasNext()) {
		    Message next = eventsSent.next();
		    if (next.isBroadCast()) {
			if (diff == -1) {
			    diff = Calendar.getInstance().getTimeInMillis() - next.getTimestamp().getTimeInMillis();
			} else {
			    if (last != null) {
				delta = diff - Math.abs((last.getTimestamp().getTimeInMillis() - next.getTimestamp().getTimeInMillis()));
				break;
			    }
			}
			last = next;
		    }
		}
	    }
	}

	MESSAGES.info("{} {} {}", msg.toString(), diff, delta);
    }

    private int getRxType(final AbstractDevice srcDevice) {
	int rxtEntity = srcDevice.getRxType();

	if (rxtEntity != 1) {
	    final Model mdl = srcDevice.getInfo().mdl;
	    final String rxTypeModel = mdl.getRxType();
	    if (rxTypeModel != null) {
		rxtEntity |= rxTypeModel.contains("b") ? 0x02 : 0;
		rxtEntity |= rxTypeModel.contains("c") ? 0x04 : 0;
		rxtEntity |= rxTypeModel.contains("w") ? 0x08 : 0;
	    }

	    if (rxtEntity != 1) {
		rxtEntity = 1;
	    }

	    srcDevice.setRxType(rxtEntity);
	}

	LOG.debug("Got RX type:" + rxtEntity);

	return rxtEntity;
    }

    private AbstractDevice infoUpdtDevData(final String src, final String p) {
	AbstractDevice dev = this.deviceStore.get(src);
	final DeviceInfo deviceInfo = DeviceInfo.parse(p);

	String name;
	if (dev != null) {
	    name = dev.getName();
	    // update info
	    dev.setInfo(deviceInfo);
	} else {
	    name = "CUL_HM_" + src;
	    if (deviceInfo.mdl != null && deviceInfo.mdl.getDeviceType() != null) {
		name = "CUL_HM_" + deviceInfo.mdl.getDeviceType().name() + "_" + src;
	    } else if (deviceInfo.mdl != null) {
		name = "CUL_HM_" + deviceInfo.mdl.name() + "_" + src;
	    }
	    dev = new DeviceFactory().createDevice(name, src, deviceInfo);
	    this.deviceStore.add(src, dev);
	    LOG.info("Auto-Defined device " + name + ".");
	}

	return dev;
    }

    /**
     * Tries to parse the event by message type.
     * 
     * @param msg
     * @param dst
     * @param src
     * @return
     * @throws IOException
     */
    private Message parseCommon(final RawMessage msg, AbstractDevice src, AbstractDevice dst) {

	// .. ..
	// CMD MT

	if (msg.getPayload().length() < 2) {
	    return null;
	}

	short chnl = -1;
	// see if channel is defined separate
	final String subType = msg.getPayload().substring(0, 2);
	if (msg.getPayload().length() >= 4) {
	    final String channel = msg.getPayload().substring(2, 4);
	    chnl = toShort(channel);
	}

	switch (msg.getMsgType()) {
	case ACK:
	    // if (srcDev.getResponseWait() != null &&
	    // srcDev.getResponseWait().getMsgId() == toInt(msg.msgCount)) {
	    // ack we waited for - stop Waiting
	    // respPendRm(srcDev);
	    // }

	    if (msg.getPayload().substring(0, 2).equals("00")) {
		// we don't have a channel here, do we?
		if (msg.getPayload().length() == 4) {
		    final BigDecimal rssi = MessageInterpreter.getRSSI(msg.getPayload());
		    return new AckStatusEvent(msg, src, dst, (short) -1, rssi);
		}
		// return simple ACK status message
		return new AckStatusEvent(msg, src, dst, (short) -1);
	    }

	    boolean success;
	    final String reply;

	    if (subType.startsWith("8")) {
		success = false;
		// srcDev.getCommandStack().clear();
		this.respPendRm(src);
	    } else {
		// ACK
		success = true;
	    }

	    // 01 01 00 00 2E
	    Matcher matcher = Pattern.compile("^(..)(..)(..)(..)(..)").matcher(msg.getPayload());

	    if (matcher.matches()) {

		final BigDecimal rssi = MessageInterpreter.getRSSI(msg.getPayload());

		return new AckStatusEvent(new AbstractMessageParameter(msg, src, dst, chnl, rssi), success);
	    }

	case SWITCH:
	    final Message lastEventSend = src.getLastEventSent();

	    if (subType.equals("01")) {
		if (lastEventSend instanceof ConfigPeerListMessage) {
		    LOG.warn("STORE PEER LIST!!!");
		    // store peer list
		    final String peers = msg.getPayload().substring(2);
		    Channelable chn = src.getChannel(chnl);
		    if (chn == null) {
			chn = src;
		    }
		    // my $chnhash = $modules{CUL_HM}{defptr}{$src.$chn};
		    // $chnhash = $shash if (!$chnhash);

		}
	    }
	    else if (subType.equals("02") || subType.equals("03")) {
		// param response
		LOG.warn("PARAM RESPONSE!!!");
		Message lastEventSent = dst.getLastEventSent();
		// if last event sent was register read we store the params here
		if (lastEventSent instanceof ConfigRegisterReadMessage) {
		    ConfigRegisterReadMessage registerReadCommand = (ConfigRegisterReadMessage) lastEventSent;
		    final short peerChannel = registerReadCommand.getPeerChannel();
		    // 0209000A0F0000
		    Channelable channel = src.getChannel(peerChannel);
		    if (channel == null) {
			channel = src;
		    }
		    short list = registerReadCommand.getPeerList();
		    if (list == -1) {
			// use default
			list = 0;
		    }
		    matcher = Utils.matcherFor(msg.getPayload(), "(..)(.*)");
		    String format = null, data = null;
		    if (matcher.matches()) {
			format = matcher.group(1);
			data = matcher.group(2);
			int bytes = data.length() / 2;
			if (bytes % 2 == 1) {
			    data = data.substring(0, data.length() - 2);
			}
		    }

		    if ("02".equals(format)) {
			// list 2: format aa:dd aa:dd ...
			matcher = Utils.matcherFor(data, "(..)(..)");
			data = matcher.replaceAll(" $1:$2");
		    } else if ("03".equals(format)) {
			// my $addr;
			// my @dataList;
			// ($addr,$data) = (hex($1),$2) if ($data =~ m/(..)(.*)/);
			// if ($addr == 0){
			// $data = "00:00";
			// }
			// else{
			// $data =~s/(..)/$1:/g;
			// foreach my $d1 (split(":",$data)){
			// push (@dataList,sprintf("%02X:%s",$addr++,$d1));
			// }
			// $data = join(" ",@dataList);
			// }
			matcher = Utils.matcherFor(data, "(..)(.*)");
			if (matcher.matches()) {
			    short addr;
			    if ((addr = toShort(matcher.group(1))) == 0) {
				data = "00:00";
			    } else {
				// $data =~s/(..)/$1:/g;
				// foreach my $d1 (split(":",$data)){
				// push (@dataList,sprintf("%02X:%s",$addr++,$d1));
				// }
				// $data = join(" ",@dataList);

				Map<Integer, String> dataMap = new HashMap<Integer, String>();

				data = matcher.group(2);

				data = data.substring(0, 2) + " " + data.substring(2);
			    }
			}
		    }

		    final String regLN = "RegL_" + list + ":" + registerReadCommand.getPeerId();
		    // store data
		    LOG.debug("Reg: {}", regLN);

		    matcher = Utils.matcherFor(data, ".* 00:00$");
		    if (matcher.matches()) {
			if (list == 0) {
			    // my $name = CUL_HM_id2Name($src);
			    // $shash->{READINGS}{PairedTo}{VAL} = sprintf("%02X%02X%02X",
			    // CUL_HM_getRegFromStore($name,10,0,"00000000"),
			    // CUL_HM_getRegFromStore($name,11,0,"00000000"),
			    // CUL_HM_getRegFromStore($name,12,0,"00000000"));
			    // $shash->{READINGS}{PairedTo}{TIME} = TimeNow();
			    final String name = src.getName();

			    LOG.debug("Paired with {}!", name);
			}

			LOG.debug("No response remaining");
			// CUL_HM_respPendRm($shash);
			// delete $chnhash->{helper}{shadowReg}{$regLN};#remove shadowhash
		    } else {

		    }
		    return new ParamResponseMessage(msg, src, dst, format, data);
		}

	    } else if (subType.equals("04")) {
		// param change
		matcher = Pattern.compile("^04(..)(........)(..)(.*)").matcher(msg.getPayload());
		if (matcher.matches()) {
		    final String peerId = matcher.group(2);
		    final int list = toInt(matcher.group(3));
		    final String data = matcher.group(4);

		    LOG.info("RegL_{}: {}", list, data);

		    final StatusChangeEvent.ChannelStatus chStatus = new StatusChangeEvent.ChannelStatus();
		    chStatus.channel = chnl;
		    chStatus.peerId = peerId;

		    return new ParamChangeMessage(msg, src, dst, chnl, peerId, list, data);
		}
	    } else if (subType.equals("06")) {
		// status request processing
		matcher = Pattern.compile("^..(..)").matcher(msg.getPayload());
		if (matcher.matches()) {
		    final String group = matcher.group(1);
		}
	    }
	}

	return null;
    }

    private void respPendRm(final AbstractDevice srcDev) {

    }

    public static RawMessage getRawMessage(final String readLine) {
	final RawMessageBuilder rawMessage = new RawMessageBuilder();

	final Pattern compile = Pattern.compile("A(..)(..)(..)(..)(......)(......)(.*)");
	final Matcher matcher = compile.matcher(readLine);
	if (matcher.matches()) {
	    rawMessage.setLength(matcher.group(1));
	    rawMessage.setMsgCount(matcher.group(2));
	    rawMessage.setMsgFlag(matcher.group(3));
	    rawMessage.setMsgType(matcher.group(4));
	    rawMessage.setSrc(matcher.group(5));
	    rawMessage.setDst(matcher.group(6));
	    rawMessage.setPayload(matcher.group(7));
	} else {
	    throw new IllegalArgumentException("No HM message format: '" + readLine + "'");
	}
	return rawMessage.build();
    }

    public static BigDecimal getRSSI(String payload) {
	// FHEM:

	// my $rssi = substr($p,8,2);# --calculate RSSI
	// CUL_HM_storeRssi($shash->{NAME},
	// ($dhash?$dhash->{NAME}:$shash->{IODev}{NAME}),
	// (-1)*(hex($rssi)))
	// if ($rssi && $rssi ne '00' && $rssi ne'80');

	// CC: -100 (204) P2
	// D1: -97.5 (209)
	// D5: -95.5
	// D7: -94.5 (215)
	// E9: -85.5
	// F2: -81
	// F5: -79.5
	// F7: -78.5 (247)

	// FD: -75.5
	// FF: -74.5 (255) P1

	// y=(-100--74.5)/(204-255)*(x-255)+-74.5
	// y=(-25.5)/(-51)*(x-255)+-74.5
	// y=1/2 * x - 127.5 +-74.5
	// y=1/2x - 202

	// 00: -74 (0) P2
	// 02: -73 (2)
	// 08: -70 (8)
	// 0A: -69 (10)
	// 0B: -68.5 ()

	// 50: -34
	// 52: -32
	// 63: -24.5
	// 70: -17
	// 71: -17.5 (113)
	// 73: -16.5
	// 94: 0 (148) P1

	// values below that rssi seem to be not reported by fhem but interpreted

	// y=(y2−y1)/(x2−x1)⋅(x−x1)+y1.

	// y=(-74-0)/(0-148)*(x-148)+0
	// y=-74/-148*(x-148)
	// y=1/2*x-74

	String rawRssi = payload.substring(payload.length() - 2);
	int rssi = toInt(rawRssi);
	int add;

	if (rssi >= 0xCC) {
	    add = 202;
	} else if (rssi <= 0x94) {
	    add = 74;
	} else {
	    return null;
	}

	return new BigDecimal(rssi).multiply(ONE_HALF).subtract(new BigDecimal(add)).round(MathContext.UNLIMITED);
    }

    public static float toFloat(final String hexString, final int begin, final int length, final float multiplicator) {
	final BigDecimal n = new BigDecimal(toInt(hexString, begin, length)).multiply(new BigDecimal(multiplicator));
	return (float) n.doubleValue();
    }

    public static BigDecimal toBigDecimal(final String hexString, final int begin, final int length, final float multiplicator) {
	return new BigDecimal(toInt(hexString, begin, length)).multiply(new BigDecimal(multiplicator));
    }

    public static int toInt(final String hexString, final int begin, final int length) {
	return toInt(hexString.substring(begin, begin + length));
    }

    public static int toInt(final String hexString) {
	return Integer.valueOf(hexString, 16);
    }

    public static short toShort(final String hexString) {
	return Short.valueOf(hexString, 16);
    }

    public static short toShort(final String hexString, final int begin, final int length) {
	return Short.valueOf(hexString.substring(begin, begin + length), 16);
    }

    public static BigDecimal toBigDecimal(String hexString) {
	return new BigDecimal(toInt(hexString));
    }

}
