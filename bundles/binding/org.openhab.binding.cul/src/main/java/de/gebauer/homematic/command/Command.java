package de.gebauer.homematic.command;

import de.gebauer.cul.homematic.out.MessageSender;
import de.gebauer.cul.homematic.out.MessageSenderImpl;
import de.gebauer.homematic.CommunicationHandler;
import de.gebauer.homematic.msg.Message;

public interface Command {

    Message getNextMessage();

    void failure();

    void success();

    Integer getCountForce();

    int getRetryCount();

    boolean hasCustomCommunicationHandler();

    CommunicationHandler getCommunicationHandler(MessageSender sender);

}
