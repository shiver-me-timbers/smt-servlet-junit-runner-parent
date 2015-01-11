package shiver.me.timbers.junit.runner.servlet.annotation;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

/**
 * This annotation is used to configure a {@link Servlet} class that hasn't already been configured. It can also be used
 * to override the configuration of a {@code Servlet} that has already been configured.
 *
 * @author Karl Bennett
 */
public @interface ServletConfiguration {

    WebServlet configuration();

    Class<? extends Servlet> servlet();
}
