package shiver.me.timbers.junit.runner.servlet.config;

import static java.lang.String.format;
import static shiver.me.timbers.junit.runner.servlet.config.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class ClassAnnotationContainerConfigFactory<C> implements ContainerConfigFactory<C> {

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {

        final Class<?> type = target.getClass();

        final shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration =
                type.getAnnotation(shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration.class);

        if (notConfigured(containerConfiguration)) {
            return NULL_CONTAINER_CONFIG;
        }

        try {
            return containerConfiguration.value().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(
                    format(
                            "The %s implementation must have a public default constructor.",
                            ContainerConfiguration.class.getName()
                    ),
                    e
            );
        }
    }

    private static boolean notConfigured(
            shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration) {
        return null == containerConfiguration || containerConfiguration.value().equals(NullContainerConfiguration.class);
    }
}
