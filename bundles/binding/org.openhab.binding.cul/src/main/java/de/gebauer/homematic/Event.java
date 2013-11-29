package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

public interface Event {

    static final String BROAD_CAST_ADDRESS = "000000";

    EventType getType();

    int getCount();

    boolean isBroadCast();

    RawMessage getRawMessage();

    Device getSender();

    Device getDestination();

}
