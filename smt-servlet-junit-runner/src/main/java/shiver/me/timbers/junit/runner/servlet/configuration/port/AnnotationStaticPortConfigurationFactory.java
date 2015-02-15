package shiver.me.timbers.junit.runner.servlet.configuration.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

/**
 * This factory will return a port configuration that sets the port according to the value in the test classes
 * {@link shiver.me.timbers.junit.runner.servlet.annotation.Port} annotation.
 *
 * @author Karl Bennett
 */
public class AnnotationStaticPortConfigurationFactory implements StaticPortConfigurationFactory {

    private final Logger log = LoggerFactory.getLogger(AnnotationStaticPortConfigurationFactory.class);

    private final AnnotationExtractor<ContainerConfiguration> annotationExtractor;

    public AnnotationStaticPortConfigurationFactory(AnnotationExtractor<ContainerConfiguration> annotationExtractor) {
        this.annotationExtractor = annotationExtractor;

        log.debug("Constructed");
    }

    @Override
    public PortConfiguration create(Object target) {

        final Class<?> type = target.getClass();

        final ContainerConfiguration configuration = annotationExtractor.create(type);

        if (portNotSet(configuration)) {
            log.debug("Port is not configured");
            return NullPortConfiguration.NULL_PORT_CONFIG;
        }

        final int port = configuration.port();

        log.debug("Port configured as {}", port);
        return new SocketPortConfiguration(port);
    }

    private static boolean portNotSet(ContainerConfiguration configuration) {
        return null == configuration || -1 >= configuration.port();
    }
}
