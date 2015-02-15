package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

import static shiver.me.timbers.junit.runner.servlet.Reflections.instantiate;
import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class ClassAnnotationContainerConfigurationFactory<C> implements ContainerConfigurationFactory<C> {

    private final Logger log = LoggerFactory.getLogger(ClassAnnotationContainerConfigurationFactory.class);

    private final AnnotationExtractor<shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration> annotationExtractor;

    public ClassAnnotationContainerConfigurationFactory(
            AnnotationExtractor<shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration> annotationExtractor
    ) {
        this.annotationExtractor = annotationExtractor;

        log.debug("Constructed");
    }

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {

        final Class<?> type = target.getClass();

        final shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration =
                annotationExtractor.create(type);

        if (notConfigured(containerConfiguration)) {
            log.debug("No container configuration found on {}", target);
            return NULL_CONTAINER_CONFIG;
        }

        final Class<? extends ContainerConfiguration> configurationClass = containerConfiguration.value();

        log.debug("Container configuration found on {}. Instantiating new {}", target, configurationClass);
        return instantiate(configurationClass);
    }

    private static boolean notConfigured(
            shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration) {
        return null == containerConfiguration || containerConfiguration.value().equals(NullContainerConfiguration.class);
    }
}
