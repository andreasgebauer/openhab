package de.gebauer.homematic;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public interface Message {

    static final String BROAD_CAST_ADDRESS = "000000";

    MessageType getType();

    int getCount();

    boolean isBroadCast();

    RawMessage getRawMessage();

    void setRawMessage(RawMessage msg);

    AbstractDevice getSender();

    AbstractDevice getDestination();

    int getChannel();

    String getPayload();

    boolean needsAck();

    boolean hasAck();

    void setAnswer(Message answer);
}
