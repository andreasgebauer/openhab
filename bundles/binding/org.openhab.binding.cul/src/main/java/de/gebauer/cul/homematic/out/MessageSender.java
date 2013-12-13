package de.gebauer.cul.homematic.out;

import java.io.IOException;

import de.gebauer.cul.homematic.out.MessageSenderImpl.WrappedMessage;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.device.AbstractDevice;

public interface MessageSender {

    /**
     * Process the command stack for the device given.
     * 
     * @param srcDevice
     *            the device to process the command stack for
     * @return the message sent
     * @throws IOException
     */
    void processCmdStack(AbstractDevice srcDevice) throws IOException;

    void send(WrappedMessage event) throws IOException;

    void tearDown();

}
