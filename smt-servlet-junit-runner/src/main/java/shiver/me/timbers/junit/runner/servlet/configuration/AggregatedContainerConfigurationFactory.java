package shiver.me.timbers.junit.runner.servlet.configuration;

import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class AggregatedContainerConfigurationFactory<C> implements ContainerConfigurationFactory<C> {

    private final ContainerConfigurationFactory<C>[] containerConfigFactories;

    @SafeVarargs
    public AggregatedContainerConfigurationFactory(ContainerConfigurationFactory<C>... containerConfigFactories) {
        this.containerConfigFactories = containerConfigFactories;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {

        for (ContainerConfigurationFactory<C> containerConfigurationFactory : containerConfigFactories) {
            final ContainerConfiguration<C> config = containerConfigurationFactory.create(target);

            if (!NULL_CONTAINER_CONFIG.equals(config)) {
                return config;
            }
        }

        return NULL_CONTAINER_CONFIG;
    }
}
