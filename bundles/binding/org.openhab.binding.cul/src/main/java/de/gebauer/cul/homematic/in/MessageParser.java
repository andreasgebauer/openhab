package de.gebauer.cul.homematic.in;

import java.io.IOException;

import de.gebauer.homematic.Event;

public interface MessageParser {

    Event parse(String readLine) throws IOException;

}
