package de.gebauer.homematic.command;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.out.MessageSender;
import de.gebauer.homematic.CommunicationHandler;
import de.gebauer.homematic.msg.Message;

public abstract class AbstractCommand implements Command {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractCommand.class);

    private final List<Message> messages = new ArrayList<Message>();

    private int curMsg = 0;

    private Integer msgCountStart;

    public AbstractCommand(Integer msgCountStart) {
	this.msgCountStart = msgCountStart;
    }

    public AbstractCommand() {
    }

    public void add(final Message message) {
	this.messages.add(message);
    }

    @Override
    public Message getNextMessage() {
	return this.messages.get(this.curMsg++);
    }

    @Override
    public void failure() {
	LOG.warn("Command was NOT successful.");
    }

    @Override
    public void success() {
	LOG.warn("Command was successful.");
    }

    @Override
    public Integer getCountForce() {
	return msgCountStart;
    }

    @Override
    public int getRetryCount() {
	return 3;
    }

    @Override
    public CommunicationHandler getCommunicationHandler(MessageSender sender) {
	return null;
    }

    @Override
    public boolean hasCustomCommunicationHandler() {
	return false;
    }
}
