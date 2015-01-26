package shiver.me.timbers.junit.runner.servlet.configuration.port;

import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

/**
 * This factory will return a port configuration that sets the port according to the value in the test classes
 * {@link shiver.me.timbers.junit.runner.servlet.annotation.Port} annotation.
 *
 * @author Karl Bennett
 */
public class AnnotationStaticPortConfigurationFactory implements StaticPortConfigurationFactory {

    private final AnnotationExtractor<ContainerConfiguration> annotationExtractor;

    public AnnotationStaticPortConfigurationFactory(AnnotationExtractor<ContainerConfiguration> annotationExtractor) {
        this.annotationExtractor = annotationExtractor;
    }

    @Override
    public PortConfiguration create(Object target) {

        final Class<?> type = target.getClass();

        final ContainerConfiguration configuration = annotationExtractor.create(type);

        if (portNotSet(configuration)) {
            return NullPortConfiguration.NULL_PORT_CONFIG;
        }

        return new SocketPortConfiguration(configuration.port());
    }

    private static boolean portNotSet(ContainerConfiguration configuration) {
        return null == configuration || -1 >= configuration.port();
    }
}
