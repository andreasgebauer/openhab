package de.gebauer.communication.cul4java;

import de.gebauer.homematic.msg.Message;
import de.tobiaswegner.communication.cul4java.CULListener;

public interface HMListener extends CULListener {

    void receivedMessage(Message event);

}
