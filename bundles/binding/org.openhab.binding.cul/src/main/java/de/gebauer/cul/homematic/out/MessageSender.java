package de.gebauer.cul.homematic.out;

import java.io.IOException;

import de.gebauer.cul.homematic.device.Command;
import de.gebauer.cul.homematic.device.Device;
import de.gebauer.homematic.Event;

public interface MessageSender {

    String sendCmd(Device sourceDevice, Command poll, int i, int j) throws IOException;

    /**
     * Process the command stack for the device given.
     * 
     * @param srcDevice
     *            the device to process the command stack for
     * @return the message sent
     * @throws IOException
     */
    String processCmdStack(Device srcDevice) throws IOException;

    void send(Event event);

}
