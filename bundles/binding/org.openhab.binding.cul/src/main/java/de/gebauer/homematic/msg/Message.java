package de.gebauer.homematic.msg;

import java.util.Calendar;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

public interface Message {

    static final String BROAD_CAST_ADDRESS = "000000";

    MessageType getType();

    int getCount();

    boolean isBroadCast();

    RawMessage getRawMessage();

    void setRawMessage(RawMessage msg);

    AbstractDevice getSource();

    AbstractDevice getDestination();

    short getChannel();

    String getPayload();

    boolean needsAck();

    boolean hasAck();

    void setResponse(Message answer);

    void setRequest(Message eventSend);

    Message getRequest();
    
    Calendar getTimestamp();

}
