package de.gebauer.homematic.command;

import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.VirtualCCU;

public abstract class AbstractCCUCommand extends AbstractCommand {

    private VirtualCCU ccu;
    private AbstractDevice dst;

    public AbstractCCUCommand(VirtualCCU ccu, AbstractDevice dst) {
	this.ccu = ccu;
	this.dst = dst;
    }

}
