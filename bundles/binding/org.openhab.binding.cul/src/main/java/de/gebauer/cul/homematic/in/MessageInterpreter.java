package de.gebauer.cul.homematic.in;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.CommandEvent;
import de.gebauer.cul.homematic.DimmerChangeEvent;
import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.device.DeviceStore;
import de.gebauer.cul.homematic.device.Model;
import de.gebauer.homematic.AckStatusEvent;
import de.gebauer.homematic.BatteryStatus;
import de.gebauer.homematic.ClimateEvent;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.DeviceInfoEvent;
import de.gebauer.homematic.Event;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.StatusChangeEvent;
import de.gebauer.homematic.StatusChangeEvent.ChannelStatus;
import de.gebauer.homematic.VDValveData;
import de.gebauer.homematic.VDValveData.MotorError;
import de.gebauer.homematic.WeatherEvent;
import de.gebauer.homematic.WindowStateEvent;

public class MessageInterpreter implements MessageParser {

    private static final Logger LOG = LoggerFactory.getLogger(MessageInterpreter.class);

    private DeviceStore deviceStore;

    public MessageInterpreter(DeviceStore deviceStore) {
	this.deviceStore = deviceStore;
    }

    public MessageInterpreter() {
	this(new DeviceStore());
    }

    public Event parse(String readLine) throws IOException {

	RawMessage msg = getRawMessage(readLine);

	Device srcDevice = this.deviceStore.get(msg.src);

	// add unknown device;
	if (srcDevice == null && MessageType.UNKNOWN.is(msg.msgType)) {
	    srcDevice = infoUpdtDevData(srcDevice, msg.src, msg.p);
	}

	if (srcDevice == null) {
	    LOG.info("Unknown device " + msg.src);
	    return null;
	}

	Model mdl = srcDevice.getInfo().mdl;

	// TODO check msgCount, msgType, src, dst, p

	// if (hmId.equals(msg.dst)) {
	// // update message counter to receiver
	// messageSender.setCmdCnt(toInt(msg.msgCount));
	// }

	Event parse = parseCommon(msg);

	// if (parse instanceof AckStatusEvent) {
	// int channel = toInt(msg.p, 0, 2);
	// if (channel == 0) {
	// return new AckStatusEvent(msg, srcDevice, channel);
	// }
	// int status = toInt(msg.p, 4, 2);
	// int rssi = toInt(msg.p, 8, 2);
	// return new AckStatusEvent(msg, srcDevice, channel, status, rssi);
	// } else

	if (MessageType.UNKNOWN.is(msg.msgType)) {
	    infoUpdtDevData(srcDevice, msg.src, msg.p);
	    return new DeviceInfoEvent(msg, srcDevice);

	} else {
	    Device dst = this.deviceStore.get(msg.dst);
	    if (mdl == Model.HMCCTC) {
		int subType = toInt(msg.p, 0, 2);
		int chnl = -1;

		if (MessageType.WEATHER.is(msg.msgType)) {
		    // 0C .. 86 70 ....... ...... .... ..
		    // len cnt ty fl src dest temp humi
		    chnl = 1;
		    float temp = toFloat(msg.p, 0, 4, 0.1f);
		    int hum = toInt(msg.p, 4, 2);
		    return new WeatherEvent(msg, srcDevice, dst, temp, hum);

		} else if (MessageType.CLIMATE.is(msg.msgType)) {
		    // 0B .. 58 A2 ....... ...... ....
		    // len cnt ty fl src dest

		    chnl = 2;
		    // valve position
		    float vp = toFloat(msg.p, 2, 2, 100f) / 256f;
		    LOG.trace("Valve Position " + toFloat(msg.p, 2, 2, 1f) + " translated to " + vp);
		    return new ClimateEvent(msg, srcDevice, dst, subType, (int) vp);

		} else if (MessageType.STATUS_CHANGE.is(msg.msgType)) {
		    Pattern ptrn = Pattern.compile("^0403(......)(..)0505(..)0000$");
		    Matcher matcher = ptrn.matcher(msg.p);
		    if (matcher.matches()) {
			String tDev = matcher.group(1);
			LOG.warn("'^0403(......)(..)0505(..)0000$' Not yet supported");
		    }

		    ptrn = Pattern.compile("^0402000000000(.)(..)(..)(..)(..)(..)(..)(..)(..)$");
		    matcher = ptrn.matcher(msg.p);
		    if (matcher.matches()) {
			int pList = toInt(matcher.group(1));
			int o1 = toInt(matcher.group(2));
			int v1 = toInt(matcher.group(3));
			int o2 = toInt(matcher.group(4));
			int v2 = toInt(matcher.group(5));
			int o3 = toInt(matcher.group(6));
			int v3 = toInt(matcher.group(7));
			int o4 = toInt(matcher.group(2));
			int v4 = toInt(matcher.group(3));

			int dayOff;
			int maxDays;
			int basevalue;
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
			    }
			}
			LOG.warn("'^0402000000000(.)(..)(..)(..)(..)(..)(..)(..)(..)$' Not yet supported.");
		    }

		    // 06
		    if (subType == 6) {
			// strip sType and chnl
			String data = msg.p.substring(4);

			// TODO what are bytes 1, 3, 4, 5, 6 ?
			int desiredTemp = toInt(data, 0, 2) / 2;
			LOG.info("Desired Temp: " + desiredTemp);

			StatusChangeEvent.ChannelStatus chStatus = new StatusChangeEvent.ChannelStatus();
			chStatus.channel = toInt(msg.p, 2, 2);
			chStatus.peerId = data.substring(2);

			return new StatusChangeEvent(msg, srcDevice, dst, chStatus);
		    }
		}
	    } else if (MessageType.COMMAND.is(msg.msgType)) {
		// command for dimmer:
		// 0201 00 00 00 OFF
		// 0201 00 03 20 FFFF 0%
		// 0201 20 03 20 FFFF 10%
		// 0201 28 03 20 FFFF 20%
		// 0201 64 03 20 FFFF 50%
		// 0201 C8 03 20 FFFF 100%
		// 0201 C8 00 00 ON

		String payload = msg.p;
		// MessageType.
		LOG.info(payload);

		int factor = toInt(payload, 4, 2) / 2;
		LOG.info("{}%", factor);
		if (payload.length() == 14 && "FFFF".equals(payload.substring(10, 14))) {

		}

		return new CommandEvent(msg, srcDevice, dst, payload);
	    } else if (mdl == Model.HMCCVD) {
		if (MessageType.ACK.is(msg.msgType)) {

		    // subtype+chn+value+err
		    Pattern ptrn = Pattern.compile("^(..)(..)(..)(..).*");
		    Matcher matcher = ptrn.matcher(msg.p);
		    if (matcher.matches()) {
			String sType = matcher.group(1);
			int chnl = toInt(matcher.group(2));
			int vp = toInt(matcher.group(3)) / 2;
			int err = toInt(matcher.group(4));
			// push @event, "actuator:$vp %";
			// $shash = $modules{CUL_HM}{defptr}{"$src$chn"}
			// if($modules{CUL_HM}{defptr}{"$src$chn"});

			int cmpVal = 0xFF;
			cmpVal = (cmpVal ^ err) | err;

			int stErr = (err >> 1) & 0x7;

			VDValveData valveData = new VDValveData();
			valveData.setValveOffset(vp);

			// if ((cmpVal & 0x0E) == 0) {
			if (stErr == 0) {
			    valveData.setMotorError(MotorError.OK);
			    valveData.setBatteryStatus(BatteryStatus.OK);
			} else {
			    if (stErr == 1) {
				LOG.info("motorErr:blocked?");
			    } else if (stErr == 2) {
				valveData.setMotorError(MotorError.LOOSE);
			    } else if (stErr == 3) {
				LOG.info("motorErr:adjusting range too small?");
			    } else if (stErr == 4) {
				LOG.info("battery:low?");
			    }
			}
			// }

			if ((err & 0x30) == 0x10) {
			    valveData.setMotorState(VDValveData.MotorState.OPENING);
			} else if ((err & 0x30) == 0x20) {
			    valveData.setMotorState(VDValveData.MotorState.CLOSING);
			} else if ((err & 0x30) == 0x00) {
			    valveData.setMotorState(VDValveData.MotorState.STOP);
			}

			return new AckStatusEvent(msg, srcDevice, dst, chnl, valveData);
		    }
		} else if (MessageType.STATUS_CHANGE.is(msg.msgType)) {
		    // CMD:A010 SRC:13F251 DST:5D24C9 0401 00000000 05 09:00
		    // 0A:07 00:00
		    // status change report to paired central unit
		    // read List5 reg 09 (offset) and 0A (err-pos)
		    // list 5 is channel-dependant not link dependant
		    // => Link discriminator (00000000) is fixed

		    Pattern compile = Pattern.compile("^0401000000000509(..)0A(..)");
		    Matcher matcher2 = compile.matcher(msg.p);

		    if (matcher2.matches()) {
			int off = toInt(matcher2.group(1));
			int vep = toInt(matcher2.group(2));

			VDValveData vdValveData = new VDValveData();
			vdValveData.setValveOffset(off);
			vdValveData.setValveErrorPosition(vep);

			// TODO return appropriate event
		    }

		    // when device is started we got the following payload:
		    // 06000000

		}
	    } else if (mdl == Model.HMSECSC) {
		if (MessageType.WINDOW.is(msg.msgType)) {
		    String payload = msg.p;
		    // 01EC00
		    // 01EDC8

		    int dontknow1 = toInt(payload, 0, 2);
		    int dontknow2 = toInt(payload, 2, 2);
		    int state = toInt(payload, 4, 2);

		    return new WindowStateEvent(msg, srcDevice, dst, state);
		} else if (MessageType.STATUS_CHANGE.is(msg.msgType)) {
		    // 06 01C800
		    // 06 01C80E
		    // 06 subtype
		    return new StatusChangeEvent(msg, srcDevice, dst, new ChannelStatus());
		}
	    } else if (mdl == Model.HMLCDIM1TPI2) {
		if (MessageType.STATUS_CHANGE.is(msg.msgType)) {
		    String payload = msg.p;
		    // after pressing button on device:
		    // 06010000 OFF
		    // 0601C800 ON
		    // TODO what is 0,1,2,3 ?
		    int dontknow1 = toInt(payload, 0, 2);
		    int dontknow2 = toInt(payload, 2, 2);
		    // 200 is on?
		    // 0 is off
		    int state = toInt(payload, 4, 2);

		    return new DimmerChangeEvent(msg, srcDevice, dst, state, dontknow1, dontknow2);
		}
	    }
	}

	return parse;
    }

    private int getRxType(Device srcDevice) {
	int rxtEntity = srcDevice.getRxType();

	if (rxtEntity != 1) {
	    Model mdl = srcDevice.getInfo().mdl;
	    String rxTypeModel = mdl.getRxType();
	    if (rxTypeModel != null) {
		rxtEntity |= (rxTypeModel.contains("b")) ? 0x02 : 0;
		rxtEntity |= (rxTypeModel.contains("c")) ? 0x04 : 0;
		rxtEntity |= (rxTypeModel.contains("w")) ? 0x08 : 0;
	    }

	    if (rxtEntity != 1) {
		rxtEntity = 1;
	    }

	    srcDevice.setRxType(rxtEntity);
	}

	LOG.debug("Got RX type:" + rxtEntity);

	return rxtEntity;
    }

    private Device infoUpdtDevData(Device srcDevice, String src, String p) {
	DeviceInfo deviceInfo = DeviceInfo.parse(p);

	if (srcDevice == null) {
	    String name = "CUL_HM_" + src;
	    if (deviceInfo.mdl != null && deviceInfo.mdl.getDeviceType() != null) {
		name = "CUL_HM_" + deviceInfo.mdl.getDeviceType().name() + "_" + src;
	    } else if (deviceInfo.mdl != null) {
		name = "CUL_HM_" + deviceInfo.mdl.name() + "_" + src;
	    }
	    srcDevice = new Device(name, src, deviceInfo);
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
    private Event parseCommon(RawMessage msg) throws IOException {
	Device srcDev = this.deviceStore.get(msg.src);
	PendType pendType = null;

	// .. ..
	// CMD MT

	if (MessageType.ACK.is(msg.msgType)) {
	    // if (srcDev.getResponseWait() != null &&
	    // srcDev.getResponseWait().getMsgId() == toInt(msg.msgCount)) {
	    // ack we waited for - stop Waiting
	    // respPendRm(srcDev);
	    // }

	    // see if channel is defined separate
	    String subType = msg.p.substring(0, 2);
	    if (msg.p.length() >= 4) {
		String channel = msg.p.substring(2, 4);
	    }

	    boolean success;
	    String reply;

	    if (subType.startsWith("8")) {
		success = false;
		eventP(srcDev, "Nack");
		// srcDev.getCommandStack().clear();
		respPendRm(srcDev);
		reply = "NACK";
	    } else {
		// ACK
		reply = subType.equals("01") ? "ACKSTatus" : "ACK";
		success = true;
	    }

	    return new AckStatusEvent(msg, srcDev, this.deviceStore.get(msg.dst), 0, null, success);
	} else if (MessageType.STATUS_CHANGE.is(msg.msgType)) {
	    String subType = msg.p.substring(0, 2);
	    if (subType.equals("01")) {
		// store peer list
		if (pendType == PendType.PEER_LIST) {
		    String peers = msg.p.substring(2);

		}
	    }
	    else if (subType.equals("02") || subType.equals("03")) {
		// param response
	    } else if (subType.equals("04")) {
		// param change
		Pattern compile = Pattern.compile("^04(..)(........)(..)(.*)");
		Matcher matcher = compile.matcher(msg.p);
		if (matcher.matches()) {
		    int chnl = toInt(matcher.group(1));
		    String peerId = matcher.group(2);
		    int list = toInt(matcher.group(3));
		    String data = matcher.group(4);

		    String listName = "RegL_" + list + ":";

		    LOG.info("data=" + data + " listName=" + listName);

		    StatusChangeEvent.ChannelStatus chStatus = new StatusChangeEvent.ChannelStatus();
		    chStatus.channel = chnl;
		    chStatus.peerId = peerId;

		    return new StatusChangeEvent(msg, srcDev, this.deviceStore.get(msg.dst), chStatus);
		}
	    } else if (subType.equals("06")) {
		// status request processing
		// TODO check if we sent a status request
		if (false) {

		} else {
		    Pattern compile = Pattern.compile("^..(..)");
		    Matcher matcher = compile.matcher(msg.p);
		    if (matcher.matches()) {
			String group = matcher.group(1);
		    }
		}

	    }
	}

	return null;
    }

    private void eventP(Device srcDev, String string) {
	// TODO Auto-generated method stub

    }

    private void respPendRm(Device srcDev) {

    }

    private RawMessage getRawMessage(String readLine) {
	RawMessage rawMessage = new RawMessage();

	Pattern compile = Pattern.compile("A(..)(..)(..)(..)(......)(......)(.*)");
	Matcher matcher = compile.matcher(readLine);
	if (matcher.matches()) {
	    rawMessage.length = matcher.group(1);
	    rawMessage.msgCount = matcher.group(2);
	    rawMessage.msgFlag = matcher.group(3);
	    rawMessage.msgType = matcher.group(4);
	    rawMessage.src = matcher.group(5);
	    rawMessage.dst = matcher.group(6);
	    rawMessage.p = matcher.group(7);
	}
	return rawMessage;
    }

    private static float toFloat(final String event, final int begin, final int length, final float multiplicator) {
	BigDecimal n = new BigDecimal(toInt(event, begin, length)).multiply(new BigDecimal(multiplicator));
	return (float) n.doubleValue();
    }

    public static int toInt(final String rawPayLoad, final int begin, final int length) {
	return toInt(rawPayLoad.substring(begin, begin + length));
    }

    public static int toInt(String hexString) {
	return Integer.valueOf(hexString, 16);
    }

}
