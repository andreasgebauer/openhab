package de.gebauer.homematic.hmccvd;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Cycle;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.msg.AbstractMessage;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageFlag;

public class ValveDevice extends AbstractDevice implements Valve {

    private static final Logger LOG = LoggerFactory.getLogger(ValveDevice.class);
    private static final DeviceMessageInterpreter interpreter = new HMCCVDInterpreter();

    private short desiredValvePos;

    public ValveDevice(String name, String id, DeviceInfo info) {
	super(name, id, info);

	// 1 device
	// 12:27:44.313
	// 12:30:08.315
	// 2:24 = 144

	// 12:38:01.574
	// 12:40:31.826
	// 2:30 = 150
	// 12:42:47.579
	// 2:16 = 136

	// 2 devices:
	// 00:45:22.630
	// 00:50:38.139
	// 05:16 = 316

	// 01:13:36.426
	// 01:18:54.685
	// 05:18 = 318

    }

    @Override
    public Method[] getCommands() {
	return Valve.class.getDeclaredMethods();
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return interpreter;
    }

    @Override
    public boolean setTo(AbstractDevice src, short valvePos) {
	// 10 = 4 %
	// 20 = 8 %
	// 50 = 19 %
	// 100 = 39 %
	// 200 = 78 %
	// 255= 99 %
	double d = valvePos * 255. / 100.;
	this.desiredValvePos = (short) Math.round(d);
	LOG.debug("Setting desired valve position for {} to {} ({})", src, valvePos, this.desiredValvePos);
	return false;
    }

    @Override
    public Message getCycleMessage(AbstractDevice source) {
	return new ClimateMessage(new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_A2).build(), source, this, this.desiredValvePos);
    }
}
