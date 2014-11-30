package shiver.me.timbers.junit.runner.servlet.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation is used to restrict the servlets that are loaded into the servlet container.
 *
 * @author Karl Bennett
 */
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface ContainerConfig {

    Class<? extends shiver.me.timbers.junit.runner.servlet.config.ContainerConfig> value() default NoContainerConfig.class;

    public static interface NoContainerConfig extends shiver.me.timbers.junit.runner.servlet.config.ContainerConfig {
    }
}
