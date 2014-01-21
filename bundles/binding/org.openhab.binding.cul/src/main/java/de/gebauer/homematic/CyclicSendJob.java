package de.gebauer.homematic;

import org.openhab.binding.cul.internal.CULBinding;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import de.gebauer.communication.cul4java.impl.HMHandler;
import de.gebauer.cul.homematic.out.MessageSenderImpl;
import de.gebauer.homematic.command.SimpleCommand;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.msg.Message;

public final class CyclicSendJob implements Job {

    @Override
    public void execute(final JobExecutionContext ctxt) throws JobExecutionException {

	try {
	    final HMHandler hmHandler = CULBinding.INSTANCE.homeMaticHandler;
	    VirtualCCU ccu = hmHandler.getCCU();

	    AbstractDevice destination = ccu.getCycle().getNextCycleDevice();
	    if (destination != null) {
		Message cycleMessage = destination.getCycleMessage(ccu);
		if (cycleMessage != null) {
		    destination.addToSendQueue(new SimpleCommand(new MessageSenderImpl.WrappedMessage(cycleMessage)));
		    hmHandler.getMessageSender().processCmdStack(destination);
		}
	    }

	    ccu.scheduleCycle();

	} catch (final Exception e) {
	    throw new JobExecutionException(e);
	}
    }
}