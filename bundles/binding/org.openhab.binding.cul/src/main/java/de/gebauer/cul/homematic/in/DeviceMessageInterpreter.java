package de.gebauer.cul.homematic.in;

import de.gebauer.homematic.Message;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Model;

public interface DeviceMessageInterpreter {

    Message read(RawMessage m, AbstractDevice src, AbstractDevice dst);

    Model getModel();

}
