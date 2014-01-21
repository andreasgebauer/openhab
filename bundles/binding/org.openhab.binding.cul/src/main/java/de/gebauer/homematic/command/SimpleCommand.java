package de.gebauer.homematic.command;

import de.gebauer.homematic.msg.Message;

/**
 * Command consisting of exactly one message.
 * 
 * @author andi
 * 
 */
public class SimpleCommand extends AbstractCommand {

    public SimpleCommand(Message message) {
	this.add(message);
    }

}
