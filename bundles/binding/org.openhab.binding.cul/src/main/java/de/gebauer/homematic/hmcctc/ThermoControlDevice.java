package de.gebauer.homematic.hmcctc;

import java.lang.reflect.Method;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.ConfigEndMessage;
import de.gebauer.homematic.msg.ConfigStartMessage;
import de.gebauer.homematic.msg.ConfigWriteMessage;
import de.gebauer.homematic.msg.HaveDataMessage;
import de.gebauer.homematic.msg.MessageFlag;

public class ThermoControlDevice extends AbstractDevice implements ThermoControl {

    private static final DeviceMessageInterpreter interpreter = new HMCCTCInterpreter();

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
	final short channel = (short) 02;

	final RawMessageBuilder msgBuilder = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_A1);

	SetControlModeCommand command = new SetControlModeCommand();
	command.add(new HaveDataMessage(msgBuilder.build(), src, this));

	// central 0110 0001 0000
	// manual 0100 0000 0000
	// auto 0108 0000 1000
	// party 0118 0001 1000
	msgBuilder.setMsgFlag(MessageFlag.VAL_A0);

	AbstractMessageParameter msgParam = new AbstractMessageParameter(msgBuilder.build(), src, this, channel);
	command.add(new ConfigStartMessage(msgParam, "000000", (short) 0, (short) 5));
	command.add(new ConfigWriteMessage(msgParam, String.format("%04X", 0x0100 + (ctrlMode.getVal() << 3))));
	command.add(new ConfigEndMessage(msgParam));

	this.addToSendQueue(command);

	return false;
    }

    @Override
    public Method[] getCommands() {
	return ThermoControl.class.getDeclaredMethods();
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return interpreter;
    }

}
