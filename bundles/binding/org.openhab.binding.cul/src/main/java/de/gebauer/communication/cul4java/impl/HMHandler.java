/**
 * 
 */
package de.gebauer.communication.cul4java.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.communication.cul4java.HMListener;
import de.gebauer.cul.homematic.VirtualCCU;
import de.gebauer.cul.homematic.in.MessageParser;
import de.gebauer.cul.homematic.out.MessageSender;
import de.gebauer.homematic.Event;
import de.tobiaswegner.communication.cul4java.CULInterface;
import de.tobiaswegner.communication.cul4java.impl.AbstractCulHandler;

/**
 * @author Andi
 * 
 */
public class HMHandler extends AbstractCulHandler<HMListener> {

    private static final Logger LOG = LoggerFactory.getLogger(HMHandler.class);

    private MessageParser messageParser;

    private VirtualCCU ccu;

    private MessageSender messageSender;

    public HMHandler(CULInterface cul, VirtualCCU ccu, MessageParser messageParser, MessageSender messageSender) {
	super(cul);
	this.ccu = ccu;
	this.messageParser = messageParser;
	this.messageSender = messageSender;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.tobiaswegner.communication.cul4java.CULHandler#parseData(java.lang
     * .String)
     */
    @Override
    public void parseData(String receivedLine) {
	try {
	    Event event = messageParser.parse(receivedLine);

	    if (event == null) {
		LOG.debug("Could not interpret " + receivedLine);
		return;
	    }

	    LOG.debug("Parsed event " + event);
	    event.getSender().addEvent(event);

	    for (HMListener listener : this.listeners) {
		listener.receivedEvent(event);
	    }

	    messageSender.processCmdStack(event.getSender());

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public char getCULReceiverPrefix() {
	return 'A';
    }

    public VirtualCCU getCCU() {
	return this.ccu;
    }

    public MessageSender getMessageSender() {
	return this.messageSender;
    }

}
