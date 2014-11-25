package org.openhab.ui.webapp.angular.internal.servlet;

import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.openhab.core.events.EventPublisher;
import org.openhab.core.items.GroupItem;
import org.openhab.core.items.Item;
import org.openhab.core.items.ItemNotFoundException;
import org.openhab.core.items.ItemRegistry;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.openhab.core.types.TypeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmdServlet extends BaseServlet {

    private static final Logger LOG = LoggerFactory.getLogger(WebAppServlet.class);

    private static final String SERVLET_NAME = "cmd";

    private EventPublisher eventPublisher;

    public void setEventPublisher(EventPublisher eventPublisher) {
	this.eventPublisher = eventPublisher;
    }

    public void unsetEventPublisher(EventPublisher eventPublisher) {
	this.eventPublisher = null;
    }

    @Override
    public void init(final ServletConfig config) throws ServletException {
	super.init(config);

	try {
	    Class<?> forName = Class.forName("org.openhab.mock.MockItemRegistryImpl");
	    Constructor<?> ctor = forName.getConstructor();
	    ctor = forName.getConstructor();
	    ctor.setAccessible(true);
	    this.itemRegistry = (ItemRegistry) ctor.newInstance();

	    this.eventPublisher = new EventPublisher() {

		@Override
		public void sendCommand(String itemName, Command command) {
		    LOG.info("Received command " + command + " for item " + itemName);
		}

		@Override
		public void postUpdate(String itemName, State newState) {

		}

		@Override
		public void postCommand(String itemName, Command command) {

		}
	    };

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	for (Object key : req.getParameterMap().keySet()) {
	    String itemName = key.toString();

	    if (!itemName.startsWith("__")) { // all additional webapp params start with "__" and should be ignored
		String commandName = req.getParameter(itemName);
		try {
		    Item item = itemRegistry.getItem(itemName);

		    // we need a special treatment for the "TOGGLE" command of switches;
		    // this is no command officially supported and must be translated
		    // into real commands by the webapp.
		    if ((item instanceof SwitchItem || item instanceof GroupItem) && commandName.equals("TOGGLE")) {
			commandName = OnOffType.ON.equals(item.getStateAs(OnOffType.class)) ? "OFF" : "ON";
		    }

		    Command command = TypeParser.parseCommand(item.getAcceptedCommandTypes(), commandName);
		    if (command != null) {
			eventPublisher.sendCommand(itemName, command);
		    } else {
			LOG.warn("Received unknown command '{}' for item '{}'", commandName, itemName);
		    }
		} catch (ItemNotFoundException e) {
		    LOG.warn("Received command '{}' for item '{}', but the item does not exist in the registry", commandName, itemName);
		}
	    }
	}
    }

    @Override
    public String getServletInfo() {
	return "Command Servlet";
    }

    @Override
    public void destroy() {
    }

    @Override
    protected String getServletName() {
	return SERVLET_NAME;
    }

}
