package de.gebauer.homematic.hmsecsc;

import static de.gebauer.cul.homematic.in.MessageInterpreter.toInt;
import static de.gebauer.cul.homematic.in.MessageInterpreter.toShort;
import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.StatusChangeEvent;
import de.gebauer.homematic.StatusChangeEvent.ChannelStatus;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Model;

public class HMSECSCInterpreter implements DeviceMessageInterpreter {

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

    @Override
    public Model getModel() {
	return Model.HMSECSC;
    }

}
