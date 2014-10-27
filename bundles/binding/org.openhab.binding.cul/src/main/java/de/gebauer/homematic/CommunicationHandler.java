package de.gebauer.homematic;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.out.MessageSender;
import de.gebauer.cul.homematic.out.MessageSenderImpl.WrappedMessage;
import de.gebauer.homematic.command.Command;
import de.gebauer.homematic.msg.Message;

public class CommunicationHandler extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(CommunicationHandler.class);

    private static final int MILLIS_WAIT_FOR_ACK = 300;
    private static final int MAX_TRIES = 3;

    private final Command command;
    private final MessageSender sender;
    private final int tries;
    private final Calendar timestampSend;

    public CommunicationHandler(final MessageSender sender, final Command message, final Calendar ts) {
	this(sender, message, ts, MAX_TRIES);
    }

    public CommunicationHandler(final MessageSender sender, final Command message, final Calendar ts, int tries) {
	this.sender = sender;
	this.command = message;
	this.timestampSend = ts;
	this.tries = tries;
    }

    @Override
    public void run() {
	Message msg = null;
	boolean success = true;
	while ((msg = this.command.getNextMessage()) != null) {

	    final WrappedMessage wrappedMsg = new WrappedMessage(msg);
	    int resendCount = 0;
	    try {
		do {
		    this.sender.send(wrappedMsg, resendCount, this.command.getCountForce());
		    resendCount++;
		    try {
			Thread.sleep(MILLIS_WAIT_FOR_ACK);
		    } catch (final InterruptedException e) {
			e.printStackTrace();
		    }
		} while (wrappedMsg.needsAck() && !wrappedMsg.hasAck() && resendCount < tries);

		if (wrappedMsg.needsAck() && !wrappedMsg.hasAck() && resendCount == tries) {
		    LOG.warn("{} retries failed. Giving up.", tries);
		    success = false;
		    break;
		}
	    } catch (final Exception e) {
		LOG.error("", e);
		success = false;
	    }
	}

	if (success) {
	    this.command.success();
	} else {
	    this.command.failure();
	}
    }
}
