package de.gebauer.homematic.device;

import de.gebauer.homematic.msg.Message;

public interface MessageSentListener {

    void messageSent(Message event);
}
