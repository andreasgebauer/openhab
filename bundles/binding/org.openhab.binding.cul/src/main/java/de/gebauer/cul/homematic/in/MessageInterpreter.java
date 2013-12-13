package de.gebauer.cul.homematic.in;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.homematic.AckStatusMessage;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.DeviceInfoEvent;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.StatusChangeEvent;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.DeviceFactory;
import de.gebauer.homematic.device.DeviceStore;
import de.gebauer.homematic.device.Model;

public class MessageInterpreter implements MessageParser {

    private static final Logger LOG = LoggerFactory.getLogger(MessageInterpreter.class);
    private static final Logger MESSAGES = LoggerFactory.getLogger("MESSAGES");

    private final DeviceStore deviceStore;

    public MessageInterpreter(final DeviceStore deviceStore) {
	this.deviceStore = deviceStore;
    }

    public MessageInterpreter() {
	this(new DeviceStore());
    }

    @Override
    public Message parse(final String readLine) throws IOException {

	final RawMessage msg = this.getRawMessage(readLine);

	MESSAGES.info(msg.toString());

	AbstractDevice srcDevice = this.deviceStore.get(msg.getSrc());

	// add unknown device;
	if (srcDevice == null && MessageType.UNKNOWN == msg.getMsgType()) {
	    srcDevice = this.infoUpdtDevData(srcDevice, msg.getSrc(), msg.getPayload());
	}

	if (srcDevice == null) {
	    LOG.info("Unknown device " + msg.getSrc());
	    return null;
	}

	// TODO check msgCount, msgType, src, dst, p

	// if (hmId.equals(msg.dst)) {
	// // update message counter to receiver
	// messageSender.setCmdCnt(toInt(msg.msgCount));
	// }

	final Message parse = this.parseCommon(msg);

	if (parse != null) {
	    return parse;
	}

	// if (parse instanceof AckStatusEvent) {
	// int channel = toInt(msg.p, 0, 2);
	// if (channel == 0) {
	// return new AckStatusEvent(msg, srcDevice, channel);
	// }
	// int status = toInt(msg.p, 4, 2);
	// int rssi = toInt(msg.p, 8, 2);
	// return new AckStatusEvent(msg, srcDevice, channel, status, rssi);
	// } else

	if (MessageType.UNKNOWN == msg.getMsgType()) {
	    this.infoUpdtDevData(srcDevice, msg.getSrc(), msg.getPayload());
	    return new DeviceInfoEvent(msg, srcDevice);

	} else {
	    final AbstractDevice dst = this.deviceStore.get(msg.getDst());

	    final DeviceMessageInterpreter interpreter = srcDevice.getInterpreter();
	    if (interpreter != null) {
		final Message read = interpreter.read(msg, srcDevice, dst);

		if (read == null) {
		    LOG.warn("Could not interpret {}", msg);
		}

		return read;
	    }

	    LOG.warn("Could not interpret {}", msg);
	}

	return null;
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

    private AbstractDevice infoUpdtDevData(AbstractDevice srcDevice, final String src, final String p) {
	final DeviceInfo deviceInfo = DeviceInfo.parse(p);

	if (srcDevice == null) {
	    String name = "CUL_HM_" + src;
	    if (deviceInfo.mdl != null && deviceInfo.mdl.getDeviceType() != null) {
		name = "CUL_HM_" + deviceInfo.mdl.getDeviceType().name() + "_" + src;
	    } else if (deviceInfo.mdl != null) {
		name = "CUL_HM_" + deviceInfo.mdl.name() + "_" + src;
	    }
	    srcDevice = new DeviceFactory().createDevice(name, src, deviceInfo);
	    this.deviceStore.add(src, srcDevice);
	    LOG.info("Auto-Defined device " + name + ".");
	}
	return srcDevice;

    }

    /**
     * Tries to parse the event by message type.
     * 
     * @param msg
     * @return
     * @throws IOException
     */
    private Message parseCommon(final RawMessage msg) throws IOException {
	final AbstractDevice src = this.deviceStore.get(msg.getSrc());
	final AbstractDevice dst = this.deviceStore.get(msg.getDst());

	final PendType pendType = null;

	// .. ..
	// CMD MT

	if (msg.getPayload().length() < 2) {
	    return null;
	}

	short chnl = -1;
	// see if channel is defined separate
	String subType = msg.getPayload().substring(0, 2);
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

	    if (msg.getPayload().equals("00")) {
		// return simple ACK status message
		return new AckStatusMessage(msg, src, dst, (short) -1);
	    }

	    boolean success;
	    String reply;

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

		final short rssi = toShort(matcher.group(5));

		return new AckStatusMessage(msg, src, dst, chnl, rssi, success);
	    }

	case SWITCH:
	    subType = msg.getPayload().substring(0, 2);
	    if (subType.equals("01")) {
		LOG.warn("STORE PEER LIST!!!");
		// store peer list
		if (pendType == PendType.PEER_LIST) {
		    final String peers = msg.getPayload().substring(2);

		}
	    }
	    else if (subType.equals("02") || subType.equals("03")) {
		// param response
		LOG.warn("PARAM RESPONSE!!!");
	    } else if (subType.equals("04")) {
		// param change
		matcher = Pattern.compile("^04(..)(........)(..)(.*)").matcher(msg.getPayload());
		if (matcher.matches()) {
		    final String peerId = matcher.group(2);
		    final int list = toInt(matcher.group(3));
		    final String data = matcher.group(4);

		    final String listName = "RegL_" + list + ":";

		    // LOG.info("data=" + data + " listName=" + listName);

		    final StatusChangeEvent.ChannelStatus chStatus = new StatusChangeEvent.ChannelStatus();
		    chStatus.channel = chnl;
		    chStatus.peerId = peerId;

		    // return new StatusChangeEvent(msg, src, dst, chStatus);
		}
	    } else if (subType.equals("06")) {
		// status request processing
		// TODO check if we sent a status request
		if (false) {

		} else {
		    matcher = Pattern.compile("^..(..)").matcher(msg.getPayload());
		    if (matcher.matches()) {
			final String group = matcher.group(1);
		    }
		}

	    }
	}

	return null;
    }

    private void respPendRm(final AbstractDevice srcDev) {

    }

    private RawMessage getRawMessage(final String readLine) {
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
	}
	return rawMessage.build();
    }

    public static float toFloat(final String event, final int begin, final int length, final float multiplicator) {
	final BigDecimal n = new BigDecimal(toInt(event, begin, length)).multiply(new BigDecimal(multiplicator));
	return (float) n.doubleValue();
    }

    public static int toInt(final String rawPayLoad, final int begin, final int length) {
	return toInt(rawPayLoad.substring(begin, begin + length));
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

}
