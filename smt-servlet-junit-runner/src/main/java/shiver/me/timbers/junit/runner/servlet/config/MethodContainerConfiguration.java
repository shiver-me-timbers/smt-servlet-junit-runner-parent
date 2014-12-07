package shiver.me.timbers.junit.runner.servlet.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class MethodContainerConfiguration<C> implements ContainerConfiguration<C> {

    private final Method method;
    private final Object target;

    public MethodContainerConfiguration(Method method, Object target) {
        this.method = method;
        this.target = target;
    }

    @Override
    public void configure(C container) {

        try {
            method.invoke(target, container);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(
                    format("The @ContainerConfiguration method (%s) must be public and only have a single argument that is of the type of the containers configuration class.",
                            method.getName()),
                    e
            );
        }
    }
}