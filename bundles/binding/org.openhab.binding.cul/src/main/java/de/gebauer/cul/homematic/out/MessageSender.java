package de.gebauer.cul.homematic.out;

import java.io.IOException;

import org.openhab.io.transport.cul.CULCommunicationException;

import de.gebauer.cul.homematic.out.MessageSenderImpl.WrappedMessage;
import de.gebauer.homematic.device.AbstractDevice;

public interface MessageSender {

    /**
     * Process the command stack of the device given.
     * 
     * @param srcDevice
     *            the device to process the command stack for
     * @return the message sent
     * @throws IOException
     */
    void processCmdStack(AbstractDevice srcDevice) throws IOException;

    void send(WrappedMessage event, int retryCount) throws CULCommunicationException;

    void tearDown();

}
