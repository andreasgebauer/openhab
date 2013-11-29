package de.gebauer.cul.homematic.device;

public class Command {

    private String command;

    public Command(String command) {
	this.command = command;
    }

    public String toString() {
	return this.command;
    }

}
