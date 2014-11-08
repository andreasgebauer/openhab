package de.gebauer.homematic.device;

import de.gebauer.homematic.msg.Message;

public interface MessageReceivedListener {

    void messageReceived(Message event);

}
