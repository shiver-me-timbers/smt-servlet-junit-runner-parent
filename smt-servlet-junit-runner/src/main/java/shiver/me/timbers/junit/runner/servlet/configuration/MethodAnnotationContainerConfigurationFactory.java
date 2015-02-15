package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class MethodAnnotationContainerConfigurationFactory<C> implements ContainerConfigurationFactory<C> {

    private final Logger log = LoggerFactory.getLogger(MethodAnnotationContainerConfigurationFactory.class);

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {

        final Class<?> type = target.getClass();

        final Method[] methods = type.getMethods();
        log.debug("Searching methods on {} for {} annotation", target, ContainerConfiguration.class);
        for (Method method : methods) {

            final ContainerConfiguration<C> config = getConfig(method, target);

            if (null != config) {
                log.debug("{} annotation found on {}'s {} method", ContainerConfiguration.class, target, method);
                return config;
            }
        }
        log.warn("No {} annotation found on {}", ContainerConfiguration.class, target);
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
