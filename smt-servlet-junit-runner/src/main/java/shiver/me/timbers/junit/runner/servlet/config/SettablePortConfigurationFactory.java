package shiver.me.timbers.junit.runner.servlet.config;

import static shiver.me.timbers.junit.runner.servlet.config.NullPortConfiguration.NULL_PORT_CONFIG;

/**
 * @author Karl Bennett
 */
public class SettablePortConfigurationFactory implements PortConfigurationFactory {

    private final StaticPortConfigurationFactory staticPortConfigFactory;
    private final RandomPortConfigFactory randomPortConfigFactory;

    public SettablePortConfigurationFactory(StaticPortConfigurationFactory staticPortConfigFactory,
                                            RandomPortConfigFactory randomPortConfigFactory) {
        this.staticPortConfigFactory = staticPortConfigFactory;
        this.randomPortConfigFactory = randomPortConfigFactory;
    }

    @Override
    public PortConfiguration create(Object target) {

        final PortConfiguration config = staticPortConfigFactory.create(target);

        if (NULL_PORT_CONFIG.equals(config)) {
            return randomPortConfigFactory.create();
        }

        return config;
    }
}
