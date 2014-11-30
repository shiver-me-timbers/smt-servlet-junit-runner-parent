package shiver.me.timbers.junit.runner.config;

import java.lang.reflect.Method;

import static shiver.me.timbers.junit.runner.config.NullContainerConfig.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class MethodAnnotationContainerConfigFactory<C> implements ContainerConfigFactory<C> {

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfig<C> create(Object target) {

        final Class<?> type = target.getClass();

        final Method[] methods = type.getMethods();

        for (Method method : methods) {

            final ContainerConfig<C> config = getConfig(method, target);

            if (null != config) {
                return config;
            }
        }

        return NULL_CONTAINER_CONFIG;
    }

    private ContainerConfig<C> getConfig(Method method, Object target) {

        final shiver.me.timbers.junit.runner.annotation.ContainerConfig containerConfig =
                method.getAnnotation(shiver.me.timbers.junit.runner.annotation.ContainerConfig.class);

        if (null == containerConfig) {
            return null;
        }

        return new MethodContainerConfig<>(method, target);
    }
}
