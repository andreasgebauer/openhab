package de.gebauer.homematic.hmlcdim1tpi2;

import de.gebauer.homematic.device.AbstractDevice;

public interface DimDevice {

    boolean off(AbstractDevice src);

    boolean on(AbstractDevice src);

    boolean dim(AbstractDevice src, short value);
}
