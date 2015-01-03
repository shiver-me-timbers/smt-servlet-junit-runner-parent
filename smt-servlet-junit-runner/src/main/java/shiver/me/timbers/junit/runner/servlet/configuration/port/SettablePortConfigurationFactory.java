package shiver.me.timbers.junit.runner.servlet.configuration.port;

import static shiver.me.timbers.junit.runner.servlet.configuration.port.NullPortConfiguration.NULL_PORT_CONFIG;

/**
 * @author Karl Bennett
 */
public class SettablePortConfigurationFactory implements PortConfigurationFactory {

    private final StaticPortConfigurationFactory staticPortConfigFactory;
    private final RandomPortConfigurationFactory randomPortConfigurationFactory;

    public SettablePortConfigurationFactory(StaticPortConfigurationFactory staticPortConfigFactory,
                                            RandomPortConfigurationFactory randomPortConfigurationFactory) {
        this.staticPortConfigFactory = staticPortConfigFactory;
        this.randomPortConfigurationFactory = randomPortConfigurationFactory;
    }

    @Override
    public PortConfiguration create(Object target) {

        final PortConfiguration config = staticPortConfigFactory.create(target);

        if (NULL_PORT_CONFIG.equals(config)) {
            return randomPortConfigurationFactory.create();
        }

        return config;
    }
}
