package de.gebauer.homematic.device;

import java.util.Calendar;

public interface Cycle {

    /**
     * Calculates the next time we want send a message to the peer.
     * 
     * 
     * @return the next time we should send a message to the peer
     */
    Calendar getNextCycle();

}
