package de.gebauer.homematic.hmccvd;

import static de.gebauer.cul.homematic.in.MessageInterpreter.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.AckStatusMessage;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.hmccvd.ValveData.MotorError;

public class HMCCVDInterpreter implements DeviceMessageInterpreter {

    // pairing
    //

    private static final Logger LOG = LoggerFactory.getLogger(HMCCVDInterpreter.class);

    @Override
    public Message read(RawMessage msg, AbstractDevice src, AbstractDevice dst) {
	if (MessageType.ACK == msg.getMsgType()) {
	    // ACK_STATUS CHANNEL:0x01 STATUS:0x00 UP:0x00 DOWN:0x00 LOWBAT:0x00
	    // RSSI:0x31) (,WAKEMEUP,RPTEN)

	    Pattern ptrn = Pattern.compile("^(..)(..)(..)(..)(..)$");
	    Matcher matcher = ptrn.matcher(msg.getPayload());
	    if (matcher.matches()) {
		// sub type is always 01???
		String sType = matcher.group(1);
		short chnl = toShort(matcher.group(2));
		short vp = (short) (toShort(matcher.group(3)) / 2);
		short err = toShort(matcher.group(4));
		short rssi = toShort(matcher.group(5));

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

		return new AckStatusMessage(msg, src, dst, chnl, rssi, valveData);
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

		new AckStatusMessage(msg, src, dst, (short) -1, (short) -1, valveData);
	    }

	    // when device is started we got the following message:
	    // RawMessage [1C475A->13C86C#00; len=0D, flag=A4, type=10,
	    // p=06000000]

	}
	return null;
    }

    @Override
    public Model getModel() {
	return Model.HMCCVD;
    }

}
