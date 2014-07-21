package de.gebauer.homematic.command;

import de.gebauer.homematic.msg.Message;

public interface Command {

    Message getNextMessage();

    void failure();

    void success();

    Integer getCountForce();

}
