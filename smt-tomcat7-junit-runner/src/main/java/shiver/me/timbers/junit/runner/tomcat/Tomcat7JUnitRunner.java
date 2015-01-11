package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.startup.Tomcat;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.AnnotationServletJUnitRunner;

import javax.servlet.ServletException;

/**
 * This JUnit test runner will start up an Embedded Tomcat instance for the related test. The
 * {@link shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration} annotation can then be used to
 * configure the Tomcat instance.
 *
 * @author Karl Bennett
 */
public class Tomcat7JUnitRunner extends AnnotationServletJUnitRunner<Tomcat> {

    public Tomcat7JUnitRunner(Class test) throws InitializationError, ServletException {
        super(new Tomcat7Container(new Tomcat()), test);
    }
}
