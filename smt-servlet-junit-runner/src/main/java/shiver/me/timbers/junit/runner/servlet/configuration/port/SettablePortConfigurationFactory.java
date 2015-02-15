package shiver.me.timbers.junit.runner.servlet.configuration.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static shiver.me.timbers.junit.runner.servlet.configuration.port.NullPortConfiguration.NULL_PORT_CONFIG;

/**
 * @author Karl Bennett
 */
public class SettablePortConfigurationFactory implements PortConfigurationFactory {

    private final Logger log = LoggerFactory.getLogger(SettablePortConfigurationFactory.class);

    private final StaticPortConfigurationFactory staticPortConfigFactory;
    private final RandomPortConfigurationFactory randomPortConfigurationFactory;

    public SettablePortConfigurationFactory(StaticPortConfigurationFactory staticPortConfigFactory,
                                            RandomPortConfigurationFactory randomPortConfigurationFactory) {
        this.staticPortConfigFactory = staticPortConfigFactory;
        this.randomPortConfigurationFactory = randomPortConfigurationFactory;

        log.debug("Constructed");
    }

    @Override
    public PortConfiguration create(Object target) {

        final PortConfiguration config = staticPortConfigFactory.create(target);

        if (NULL_PORT_CONFIG.equals(config)) {
            log.debug("Port not manually set, choosing one randomly");
            return randomPortConfigurationFactory.create();
        }
        log.debug("Port manually set to {}", config.getPort());
        return config;
    }
}
