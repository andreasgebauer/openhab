package de.gebauer.homematic;

import java.io.IOException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.out.MessageSender;
import de.gebauer.cul.homematic.out.MessageSenderImpl.WrappedMessage;

public class CommunicationHandler extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(CommunicationHandler.class);

    private WrappedMessage message;
    private Calendar timestampSend;
    private MessageSender sender;

    public CommunicationHandler(MessageSender sender, WrappedMessage message, Calendar ts) {
	this.sender = sender;
	this.message = message;
	this.timestampSend = ts;
    }

    @Override
    public void run() {
	try {
	    sender.send(message);
	    message.resendCount++;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
