package shiver.me.timbers.junit.runner.servlet.config;

import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import static shiver.me.timbers.junit.runner.servlet.config.NullPortConfiguration.NULL_PORT_CONFIG;

/**
 * This factory will return a port config that sets the port according to the value in the test classes
 * {@link shiver.me.timbers.junit.runner.servlet.annotation.Port} annotation.
 *
 * @author Karl Bennett
 */
public class AnnotationStaticPortConfigurationFactory implements StaticPortConfigurationFactory {

    @Override
    public PortConfiguration create(Object target) {

        final Class<?> type = target.getClass();

        final ContainerConfiguration configuration = type.getAnnotation(ContainerConfiguration.class);

        if (portNotSet(configuration)) {
            return NULL_PORT_CONFIG;
        }

        return new SocketPortConfiguration(configuration.port());
    }

    private static boolean portNotSet(ContainerConfiguration configuration) {
        return null == configuration || -1 >= configuration.port();
    }
}
