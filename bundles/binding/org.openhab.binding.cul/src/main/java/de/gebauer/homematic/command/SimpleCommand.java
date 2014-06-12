package de.gebauer.homematic.command;

import de.gebauer.homematic.msg.Message;

/**
 * Command consisting of exactly one message.
 * 
 * @author andi
 * 
 */
public class SimpleCommand extends AbstractCommand {

    private Integer msgCount;

    public SimpleCommand(Message message, Integer msgCnt) {
	this(message);
	msgCount = msgCnt;
    }

    public SimpleCommand(Message message) {
	this.add(message);
    }

    @Override
    public Integer getCountForce() {
	return msgCount;
    }

}
