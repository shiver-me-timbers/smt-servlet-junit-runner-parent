package shiver.me.timbers.junit.runner.servlet.configuration;

import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

import static shiver.me.timbers.junit.runner.servlet.Reflections.instantiate;
import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class ClassAnnotationContainerConfigurationFactory<C> implements ContainerConfigurationFactory<C> {

    private final AnnotationExtractor<shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration> annotationExtractor;

    public ClassAnnotationContainerConfigurationFactory(
            AnnotationExtractor<shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration> annotationExtractor
    ) {
        this.annotationExtractor = annotationExtractor;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {

        final Class<?> type = target.getClass();

        final shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration =
                annotationExtractor.create(type);

        if (notConfigured(containerConfiguration)) {
            return NULL_CONTAINER_CONFIG;
        }

        return instantiate(containerConfiguration.value());
    }

    private static boolean notConfigured(
            shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration) {
        return null == containerConfiguration || containerConfiguration.value().equals(NullContainerConfiguration.class);
    }
}
