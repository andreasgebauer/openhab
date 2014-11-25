package org.openhab.binding.boblight4j.internal;

import java.util.Dictionary;

import org.boblight4j.client.AbstractFlagManager;
import org.boblight4j.client.CommandLineArgs;
import org.boblight4j.client.LightsHolderImpl;
import org.boblight4j.client.RemoteClient;
import org.boblight4j.client.SocketClientImpl;
import org.openhab.binding.boblight4j.internal.binding.Boblight4JBindingConfig;
import org.openhab.core.binding.AbstractActiveBinding;
import org.openhab.core.library.types.HSBType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.types.Command;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Boblight4JBinding extends AbstractActiveBinding<Boblight4JGenericBindingProvider> implements ManagedService {

    private static final Logger logger = LoggerFactory.getLogger(Boblight4JBinding.class);

    private RemoteClient remoteClient;

    private String host;

    private Boblight4JGenericBindingProvider provider;

    @Override
    protected void execute() {

	// TODO Auto-generated method stub

    }

    @Override
    protected long getRefreshInterval() {
	return 10000;
    }

    @Override
    protected String getName() {
	return "Boblight4JBinding Refresh Service";
    }

    @Override
    public void addBindingProvider(Boblight4JGenericBindingProvider provider) {
	super.addBindingProvider(provider);
	this.provider = provider;
    }

    @Override
    public void updated(final Dictionary<String, ?> properties) throws ConfigurationException {

	if (properties == null) {
	    return;
	}

	final String host = (String) properties.get("host");
	if (host != null) {
	    this.host = host;
	    this.setup(host);
	}
    }

    private void setup(final String host) {
	if (this.remoteClient != null) {
	    this.remoteClient.destroy();
	}

	this.remoteClient = new SocketClientImpl(new LightsHolderImpl());
	this.remoteClient.setup(new AbstractFlagManager<CommandLineArgs>() {
	    @Override
	    protected CommandLineArgs getArgBean() {
		return new CommandLineArgs() {
		    @Override
		    public Server getServer() {
			return new Server(host);
		    }
		};
	    }
	});
    }

    @Override
    protected void internalReceiveCommand(final String itemName, final Command command) {
	logger.debug("Received command {} for item {}", command, itemName);

	if (this.remoteClient == null && this.host != null) {
	    this.setup(this.host);
	}

	Boblight4JBindingConfig config = this.provider.getConfig(itemName);
	if (this.remoteClient == null) {
	    logger.warn("Could not setup client. Unable to execute command");
	} else {
	    try {
		if (command instanceof HSBType) {
		    final PercentType red = ((HSBType) command).getRed();
		    final PercentType green = ((HSBType) command).getGreen();
		    final PercentType blue = ((HSBType) command).getBlue();

		    // load the color into int array
		    final int rgb[] = new int[] { red.intValue(), green.intValue(), blue.intValue() };
		    if (config.getBindingConfig().equals("All")) {
			// set all lights to the color we want and send it
			this.remoteClient.getLightsHolder().addPixel(null, rgb);
		    } else {
			for (String lightName : config.getBindingConfig().split(",")) {
			    this.remoteClient.getLightsHolder().addPixel(lightName, rgb);
			}
		    }

		    this.remoteClient.sendRgb(true, null);
		}

	    } catch (final Exception e) {
		logger.error("Exception occured during add pixel or sending rgb values. Destroying client.", e);
		this.remoteClient.destroy();
		this.remoteClient = null;
	    }
	}
    }

}
