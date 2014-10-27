package de.gebauer.homematic.hmlcsw1pbufm;

import de.gebauer.homematic.device.AbstractDevice;

public interface Switch {

    boolean off(AbstractDevice src);

    boolean on(AbstractDevice src);

}
