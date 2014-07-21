package de.gebauer.cul.homematic.in;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.Message;

public interface DeviceMessageInterpreter {

    Message read(RawMessage m, AbstractDevice src, AbstractDevice dst);

}
