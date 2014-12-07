package shiver.me.timbers.junit.runner.servlet.configuration;

import java.lang.reflect.Method;

import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class MethodAnnotationContainerConfigurationFactory<C> implements ContainerConfigurationFactory<C> {

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {

        final Class<?> type = target.getClass();

        final Method[] methods = type.getMethods();

        for (Method method : methods) {

            final ContainerConfiguration<C> config = getConfig(method, target);

            if (null != config) {
                return config;
            }
        }

        return NULL_CONTAINER_CONFIG;
    }

    private ContainerConfiguration<C> getConfig(Method method, Object target) {

        final shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration =
                method.getAnnotation(shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration.class);

        if (null == containerConfiguration) {
            return null;
        }

        return new MethodContainerConfiguration<>(method, target);
    }
}
