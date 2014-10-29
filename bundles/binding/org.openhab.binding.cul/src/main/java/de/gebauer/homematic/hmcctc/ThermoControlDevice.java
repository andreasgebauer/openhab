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

	msgBuilder.setMsgFlag(MessageFlag.VAL_A0);

	AbstractMessageParameter msgParam = new AbstractMessageParameter(msgBuilder.build(), src, this, channel);
	command.add(new ConfigStartMessage(msgParam, "000000", (short) 0, (short) 5));
	//
	// cent 0110 0001 0000
	// auto 0108 0000 1000
	// manu 0100 0000 0000
	// part 0118 0001 1000

	// 2014-10-29 got these values when sniffing FHEM:
	// cent 0120 0010 0000 (0x00)
	// auto 0128 0010 1000 (0x08)
	// manu 0130 0011 0000 (0x10)
	// part 0138 0011 1000 (0x18)

	// CENTRAL: 0120 (00)
	// AUTO: 0128 (01)
	// MANUAL: 0130 (10)
	// PARTY: 0138 (11)

	int magic = 0x0120;
	int i = (ctrlMode.getVal() << 3);

	String dataString = String.format("%04X", magic + i);
	command.add(new ConfigWriteMessage(msgParam, dataString));
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
