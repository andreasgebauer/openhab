package de.gebauer.cul.homematic.in;

import java.io.IOException;

import de.gebauer.homematic.Message;

public interface MessageParser {

    Message parse(String readLine) throws IOException;

}
