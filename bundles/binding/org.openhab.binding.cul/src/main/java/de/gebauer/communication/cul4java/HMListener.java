package de.gebauer.communication.cul4java;

import de.gebauer.homematic.Event;
import de.tobiaswegner.communication.cul4java.CULListener;

public interface HMListener extends CULListener {

    void receivedEvent(Event event);

}
