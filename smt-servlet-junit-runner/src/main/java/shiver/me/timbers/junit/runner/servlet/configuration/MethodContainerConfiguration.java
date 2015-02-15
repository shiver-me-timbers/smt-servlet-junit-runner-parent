package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class MethodContainerConfiguration<C> implements ContainerConfiguration<C> {

    private final Logger log = LoggerFactory.getLogger(MethodContainerConfiguration.class);

    private final Method method;
    private final Object target;

    public MethodContainerConfiguration(Method method, Object target) {
        this.method = method;
        this.target = target;

        log.debug("Constructed");
    }

    @Override
    public void configure(C container) {

        try {
            log.debug("Invoking {}'s {} method on {}", target, method, container);
            method.invoke(target, container);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(
                    format("The @ContainerConfiguration method (%s) must be public and only have a single argument " +
                            "that is of the type of the containers configuration class.",
                            method.getName()),
                    e
            );
        }
    }
}
