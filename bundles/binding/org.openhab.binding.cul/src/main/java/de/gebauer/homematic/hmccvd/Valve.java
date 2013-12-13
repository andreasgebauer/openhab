package de.gebauer.homematic.hmccvd;

import de.gebauer.homematic.device.AbstractDevice;

public interface Valve {

    void setTo(AbstractDevice src, short valvePos);
}
