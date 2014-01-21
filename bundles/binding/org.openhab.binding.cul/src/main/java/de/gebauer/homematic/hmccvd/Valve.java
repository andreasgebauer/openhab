package de.gebauer.homematic.hmccvd;

import de.gebauer.homematic.device.AbstractDevice;

public interface Valve {

    boolean setTo(AbstractDevice src, short valvePos);
}
