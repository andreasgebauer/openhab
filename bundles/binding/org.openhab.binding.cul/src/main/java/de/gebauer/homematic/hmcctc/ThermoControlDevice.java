package de.gebauer.homematic.hmcctc;

import java.lang.reflect.Method;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.ConfigEndCommand;
import de.gebauer.homematic.ConfigStartCommand;
import de.gebauer.homematic.ConfigWriteCommand;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.HaveDataMessage;
import de.gebauer.homematic.MessageFlag;
import de.gebauer.homematic.device.AbstractDevice;

public class ThermoControlDevice extends AbstractDevice implements ThermoControl {

    private final DeviceMessageInterpreter interpreter = new HMCCTCInterpreter();

    public ThermoControlDevice(final String name, final String id, final DeviceInfo info) {
	super(name, id, info);
    }

    /**
     * <pre>
     * L:09 N:AC F:A1 CMD:12 SRC:13C86C DST:1EA808 (HAVE_DATA) (,WAKEUP,BIDI,RPTEN) 
     * L:10 N:AD F:A0 CMD:01 SRC:13C86C DST:1EA808 02050000000005 (CONFIG_START CHANNEL:0x02 PEER_ADDRESS:0x000000 PEER_CHANNEL:0x00 PARAM_LIST:0x05) (,BIDI,RPTEN)
     * L:0D N:AE F:A0 CMD:01 SRC:13C86C DST:1EA808 02080100 (CONFIG_WRITE_INDEX CHANNEL:0x02 DATA:0x0100) (,BIDI,RPTEN)
     * L:0B N:AF F:A0 CMD:01 SRC:13C86C DST:1EA808 0206 (CONFIG_END CHANNEL:0x02) (,BIDI,RPTEN)
     * </pre>
     * 
     * @param src
     * @param ctrlMode
     */
    @Override
    public boolean controlMode(final AbstractDevice src, final ControlMode ctrlMode) {
	final RawMessageBuilder msgBuilder = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_A1);
	this.addToSendQueue(new HaveDataMessage(msgBuilder.build(), src, this));

	final short channel = (short) 02;
	msgBuilder.setMsgFlag(MessageFlag.VAL_A0);
	this.addToSendQueue(new ConfigStartCommand(msgBuilder.build(), src, this, channel, 0, (short) 0, (short) 5));
	this.addToSendQueue(new ConfigWriteCommand(msgBuilder.build(), src, this, channel, String.format("%04X", 0x0100 + ctrlMode.getVal())));
	this.addToSendQueue(new ConfigEndCommand(msgBuilder.build(), src, this, channel));

	return false;
    }

    @Override
    public Method[] getCommands() {
	return ThermoControl.class.getDeclaredMethods();
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return this.interpreter;
    }

}
