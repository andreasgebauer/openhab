package de.gebauer.homematic.device;

import java.util.Calendar;

public interface Cycle {

    Calendar getNextCycle(int messageCount);

    AbstractDevice getNextCycleDevice();

}
