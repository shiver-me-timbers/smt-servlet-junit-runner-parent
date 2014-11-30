package shiver.me.timbers.junit.runner.servlet.config;

import static java.lang.String.format;
import static shiver.me.timbers.junit.runner.servlet.config.NullContainerConfig.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class ClassAnnotationContainerConfigFactory<C> implements ContainerConfigFactory<C> {

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfig<C> create(Object target) {

        final Class<?> type = target.getClass();

        final shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfig containerConfig =
                type.getAnnotation(shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfig.class);

        if (null == containerConfig) {
            return NULL_CONTAINER_CONFIG;
        }

        try {
            return containerConfig.value().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(
                    format(
                            "The %s implementation must have a public default constructor.",
                            ContainerConfig.class.getName()
                    ),
                    e
            );
        }
    }
}
