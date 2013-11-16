package org.openhab.binding.cul.internal.binding;

import java.util.Set;

import org.openhab.core.items.Item;
import org.openhab.core.types.Command;

import de.tobiaswegner.communication.cul4java.CULInterface;

public class HomeMaticBindingConfig extends AbstractCulBindingConfig {

    public HomeMaticBindingConfig(Item item, String houseCode) {
	super(item, houseCode);
    }

    @Override
    public String getAddress() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Set<Class<? extends Item>> getAllowedItemTypes() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean isCommandAllowed(Command command) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void executeCommand(CULInterface cul, Command command) {
    }

    @Override
    public boolean isWriteable() {
	return false;
    }

}
