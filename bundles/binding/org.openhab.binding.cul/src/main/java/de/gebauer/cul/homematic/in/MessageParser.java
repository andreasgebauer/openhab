package de.gebauer.cul.homematic.in;

import java.io.IOException;

import de.gebauer.homematic.msg.Message;

public interface MessageParser {

    Message parse(String readLine) throws IOException;

}
