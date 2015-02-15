package shiver.me.timbers.junit.runner.servlet.configuration.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Karl Bennett
 */
public class FreeRandomPortConfigurationFactory implements RandomPortConfigurationFactory {

    private final Logger log = LoggerFactory.getLogger(FreeRandomPortConfigurationFactory.class);

    @Override
    public PortConfiguration create() {

        final SocketPortConfiguration configuration = new SocketPortConfiguration();

        log.debug("Random port configured to be {}", configuration.getPort());
        return configuration;
    }
}
