package shiver.me.timbers.junit.runner.servlet.annotation;

import javax.servlet.Servlet;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation is used to restrict the servlets that are loaded into the servlet container.
 *
 * @author Karl Bennett
 */
@Target({FIELD, TYPE})
@Retention(RUNTIME)
public @interface Servlets {

    Class<? extends Servlet>[] value() default {};
}
