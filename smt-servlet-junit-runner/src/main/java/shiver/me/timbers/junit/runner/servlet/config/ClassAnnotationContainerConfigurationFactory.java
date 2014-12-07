package shiver.me.timbers.junit.runner.servlet.config;

import static shiver.me.timbers.junit.runner.servlet.Reflections.instantiate;
import static shiver.me.timbers.junit.runner.servlet.config.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class ClassAnnotationContainerConfigurationFactory<C> implements ContainerConfigurationFactory<C> {

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {

        final Class<?> type = target.getClass();

        final shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration =
                type.getAnnotation(shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration.class);

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
