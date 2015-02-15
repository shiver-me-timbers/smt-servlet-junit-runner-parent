package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * This ContainerConfigurationFactory will try each of it's configured factories until one of them returns a custom
 * configuration.
 *
 * @author Karl Bennett
 */
public class ConsecutiveContainerConfigurationFactory<C> implements ContainerConfigurationFactory<C> {

    private final Logger log = LoggerFactory.getLogger(ConsecutiveContainerConfigurationFactory.class);

    private final ContainerConfigurationFactory<C>[] containerConfigFactories;

    @SafeVarargs
    public ConsecutiveContainerConfigurationFactory(ContainerConfigurationFactory<C>... containerConfigFactories) {
        this.containerConfigFactories = containerConfigFactories;

        log.debug("Constructed");
    }

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {
        log.debug("Searching for configurations on {}", target);
        for (ContainerConfigurationFactory<C> containerConfigurationFactory : containerConfigFactories) {
            log.debug("Trying {} on {}", containerConfigurationFactory, target);
            final ContainerConfiguration<C> config = containerConfigurationFactory.create(target);

            if (!NULL_CONTAINER_CONFIG.equals(config)) {
                log.debug("Found configuration with {} on {}", containerConfigurationFactory, target);
                return config;
            }
        }
        log.debug("No configuration found on {}", target);
        return NULL_CONTAINER_CONFIG;
    }
}
