package shiver.me.timbers.junit.runner;

import static shiver.me.timbers.junit.runner.NullSocketConfig.NULL_PORT_CONFIG;

/**
 * @author Karl Bennett
 */
public class SettableSocketConfigFactory implements SocketConfigFactory {

    private final StaticSocketConfigFactory staticPortConfigFactory;
    private final RandomPortConfigFactory randomPortConfigFactory;

    public SettableSocketConfigFactory(StaticSocketConfigFactory staticPortConfigFactory,
                                       RandomPortConfigFactory randomPortConfigFactory) {
        this.staticPortConfigFactory = staticPortConfigFactory;
        this.randomPortConfigFactory = randomPortConfigFactory;
    }

    @Override
    public SocketConfig create(Object target) {

        final SocketConfig config = staticPortConfigFactory.create(target);

        if (NULL_PORT_CONFIG.equals(config)) {
            return randomPortConfigFactory.create();
        }

        return config;
    }
}
