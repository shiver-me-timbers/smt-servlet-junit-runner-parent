package shiver.me.timbers.junit.runner.servlet.config;

import static shiver.me.timbers.junit.runner.servlet.config.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class AggregatedContainerConfigFactory<C> implements ContainerConfigFactory<C> {

    private final ContainerConfigFactory<C>[] containerConfigFactories;

    @SafeVarargs
    public AggregatedContainerConfigFactory(ContainerConfigFactory<C>... containerConfigFactories) {
        this.containerConfigFactories = containerConfigFactories;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {

        for (ContainerConfigFactory<C> containerConfigFactory : containerConfigFactories) {
            final ContainerConfiguration<C> config = containerConfigFactory.create(target);

            if (!NULL_CONTAINER_CONFIG.equals(config)) {
                return config;
            }
        }

        return NULL_CONTAINER_CONFIG;
    }
}
