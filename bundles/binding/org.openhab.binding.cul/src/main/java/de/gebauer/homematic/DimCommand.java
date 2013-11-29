package de.gebauer.homematic;

import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.in.RawMessage;

/**
 * <pre>
 * OFF
 * 0E 09 A0 11 13C86C 20E916 0201000000
 * 0E 09 80 02 20E916 13C86C 0101000040
 * 
 * 0E 11 A0 11 13C86C 20E916 0201000000
 * 0E 11 80 02 20E916 13C86C 0101000044
 * 
 * 0E 12 A0 11 13C86C 20E916 0201000000
 * 0E 12 80 02 20E916 13C86C 0101000042
 * 
 * ON
 * 0E 0A A0 11 13C86C 20E916 0201000000
 * 0E 0A 80 02 20E916 13C86C 0101000044
 * 
 * 0E 10 A0 11 13C86C 20E916 0201C80000
 * 0E 10 80 02 20E916 13C86C 0101C8003F
 * 
 * 0E 13 A0 11 13C86C 20E916 0201C80000
 * 0E 13 80 02 20E916 13C86C 0101C80044
 * 
 * 0%
 * 10 0E A0 11 13C86C 20E916 0201 00 03 20FFFF
 * 0E 0E 80 02 20E916 13C86C 0101 00 00 48
 * 0D 0F A4 10 20E916 13C86C 0601 00 00
 * 0A 0F 80 02 13C86C 20E916 00
 * 
 * 10 14 A0 11 13C86C 20E916 02 01 00 03 20FFFF
 * 0E 14 80 02 20E916 13C86C 01 01 C5 20 3D
 * 0D 15 A4 10 20E916 13C86C 06 01 00 00
 * 0A 15 80 02 13C86C 20E916 00
 * 
 * 10 01 A0 11 13C86D 20E916 02 01 000320FFFF
 * 0E 01 80 02 20E916 13C86D 01 01 00003A
 * 
 * 100%
 * 10 16 A0 11 13C86C 20E916 02 01 C8 03 20FFFF
 * 0E 16 80 02 20E916 13C86C 01 01 01 10 47
 * 0D 17 A4 10 20E916 13C86C 06 01 C800
 * 0A 17 80 02 13C86C 20E916 00
 * 
 * </pre>
 * 
 * @author Andreas Gebauer
 * 
 */
public class DimCommand extends AbstractEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Dim command for states ON and OFF.
     * 
     * @param msg
     *            the raw message.
     * @param srcDevice
     * @param dstDevice
     * @param state
     */
    public DimCommand(Device srcDevice, Device dstDevice, boolean state) {
	super(new RawMessage(), srcDevice, dstDevice);
	msg.p = String.format("0201%02X0000", state ? 200 : 0);
    }

    /**
     * Dim command for a percent value.
     * 
     * @param srcDevice
     * @param dstDevice
     * @param dimFactor
     */
    public DimCommand(Device srcDevice, Device dstDevice, int dimFactor) {
	super(new RawMessage(), srcDevice, dstDevice);
	if (dimFactor < 0 || dimFactor > 100) {
	    throw new IllegalArgumentException("Argument dimFactor must be between 0 and 100.");
	}
	msg.p = String.format("0201%02X0320FFFF", dimFactor * 2);
    }

    @Override
    public EventType getType() {
	return EventType.DIMM;
    }

}
