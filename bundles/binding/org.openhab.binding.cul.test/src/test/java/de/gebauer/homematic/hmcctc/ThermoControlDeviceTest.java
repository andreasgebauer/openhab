package de.gebauer.homematic.hmcctc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.command.Command;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.msg.HaveDataMessage;
import de.gebauer.homematic.msg.Message;

public class ThermoControlDeviceTest {

    private ThermoControlDevice device;

    @Before
    public void setUp() {
	this.device = new ThermoControlDevice(null, "12345678", new DeviceInfo("2.2", Model.HMCCTC, "12345"));
    }

    // OpenHAB:
    // /A 0CEE8670 1EA808 00000000DC3E37
    // As 09EEA112 13C86D 1EA808

    // FHEM:
    // /A 0CEB8670 1EA808 00000000DC3E29
    // As 0908A112 13C86D 1EA808
    // /A 0A088002 1EA808 13C86D0028

    // Set control mode
    // As 10 03 A001 13C86D 1EA808 02050000000005
    // /A 0A 03 8002 1EA808 13C86D 00 02
    // As 0D 04 A001 13C86D 1EA808 020801..
    // /A 0A 04 8002 1EA808 13C86D 00 01
    // As 0B 05 A001 13C86D 1EA808 0206
    // /A 0A 05 8002 1EA808 13C86D 00 00
    // FHEM sends this as last command but it is not answered
    // As0F06803F 13C86D 1EA808 02041BE37B42

    @Test
    public void testSetControlModeCentral() {

	device.controlMode(null, ControlMode.CENTRAL);

	Queue<Command> commandStack = device.getCommandStack();

	assertEquals(1, commandStack.size());
	Command poll = commandStack.poll();
	assertTrue(poll instanceof SetControlModeCommand);

	SetControlModeCommand setControlCmd = (SetControlModeCommand) poll;

	assertEquals("", setControlCmd.getNextMessage().getPayload());
	assertEquals("02050000000005", setControlCmd.getNextMessage().getPayload());
	assertEquals("02080120", setControlCmd.getNextMessage().getPayload());
	assertEquals("0206", setControlCmd.getNextMessage().getPayload());

    }

    @Test
    public void testSetControlModeManual() {

	device.controlMode(null, ControlMode.MANUAL);

	Queue<Command> commandStack = device.getCommandStack();

	assertEquals(1, commandStack.size());
	Command poll = commandStack.poll();
	assertTrue(poll instanceof SetControlModeCommand);

	SetControlModeCommand setControlCmd = (SetControlModeCommand) poll;

	assertEquals("", setControlCmd.getNextMessage().getPayload());
	assertEquals("02050000000005", setControlCmd.getNextMessage().getPayload());
	assertEquals("02080130", setControlCmd.getNextMessage().getPayload());
	assertEquals("0206", setControlCmd.getNextMessage().getPayload());

    }

    @Test
    public void testSetControlModeAuto() {

	device.controlMode(null, ControlMode.AUTO);

	Queue<Command> commandStack = device.getCommandStack();

	assertEquals(1, commandStack.size());
	Command poll = commandStack.poll();
	assertTrue(poll instanceof SetControlModeCommand);

	SetControlModeCommand setControlCmd = (SetControlModeCommand) poll;

	assertEquals("", setControlCmd.getNextMessage().getPayload());
	assertEquals("02050000000005", setControlCmd.getNextMessage().getPayload());
	assertEquals("02080128", setControlCmd.getNextMessage().getPayload());
	assertEquals("0206", setControlCmd.getNextMessage().getPayload());

    }

    @Test
    public void testSetControlModeParty() {

	device.controlMode(null, ControlMode.PARTY);

	Queue<Command> commandStack = device.getCommandStack();

	assertEquals(1, commandStack.size());
	Command poll = commandStack.poll();
	assertTrue(poll instanceof SetControlModeCommand);

	SetControlModeCommand setControlCmd = (SetControlModeCommand) poll;

	assertEquals("", setControlCmd.getNextMessage().getPayload());
	assertEquals("02050000000005", setControlCmd.getNextMessage().getPayload());
	assertEquals("02080138", setControlCmd.getNextMessage().getPayload());
	assertEquals("0206", setControlCmd.getNextMessage().getPayload());

    }

}
