package shiver.me.timbers.junit.runner.config;

import static shiver.me.timbers.junit.runner.config.NullPortConfig.NULL_PORT_CONFIG;

/**
 * @author Karl Bennett
 */
public class SettablePortConfigFactory implements PortConfigFactory {

    private final StaticPortConfigFactory staticPortConfigFactory;
    private final RandomPortConfigFactory randomPortConfigFactory;

    public SettablePortConfigFactory(StaticPortConfigFactory staticPortConfigFactory,
                                     RandomPortConfigFactory randomPortConfigFactory) {
        this.staticPortConfigFactory = staticPortConfigFactory;
        this.randomPortConfigFactory = randomPortConfigFactory;
    }

    @Override
    public PortConfig create(Object target) {

        final PortConfig config = staticPortConfigFactory.create(target);

        if (NULL_PORT_CONFIG.equals(config)) {
            return randomPortConfigFactory.create();
        }

        return config;
    }
}
