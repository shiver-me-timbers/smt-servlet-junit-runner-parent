package shiver.me.timbers.junit.runner.servlet;

import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.configuration.ClassAnnotationContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.CompositeContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.MethodAnnotationContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.filter.FiltersContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.AnnotationStaticPortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.FreeRandomPortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.SettablePortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.servlet.ServletsContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationPortSetter;

/**
 * Adding this runner to a JUnit class will cause a servlet server to start up before the test.
 * <p/>
 * The server will start up on a random free port which can be accessed within the test by annotating an
 * {@code Integer}/{@code int} field with {@link shiver.me.timbers.junit.runner.servlet.annotation.Port}. The port
 * number can be set manually by instead annotating the test class with with {@code Port} and setting it's value to the
 * desired port number.
 * <p/>
 * By default the started server will scan the package of the test file for any classes annotated with
 * {@link javax.servlet.annotation.WebServlet} which it will then load.
 * <p/>
 * Alternatively the {@link shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration} annotation can be
 * used to restrict which servlet classes are loaded.
 * <p/>
 * The server can be configured for an individual test class with a method that has the servlet containers configuration
 * object as it's first argument and has been annotated with
 * {@link shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration}. This will cause a new server
 * instance to start up for that specific test class.
 * <p/>
 * If the same configuration can be used across multiple test classes then the classes can be annotated with
 * {@code ContainerConfiguration} that has it's value set to an implementation of
 * {@link shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration}.
 *
 * @author Karl Bennett
 */
public class AnnotationServletJUnitRunner<C> extends ServletJUnitRunner<C> {

    public AnnotationServletJUnitRunner(Container<C> container, Class test) throws InitializationError {
        super(
                new SettablePortConfigurationFactory(
                        new AnnotationStaticPortConfigurationFactory(),
                        new FreeRandomPortConfigurationFactory()
                ),
                new ServletsContainerConfigurationAnnotationFactory(),
                new FiltersContainerConfigurationAnnotationFactory(),
                new CompositeContainerConfigurationFactory<>(
                        new ClassAnnotationContainerConfigurationFactory<C>(),
                        new MethodAnnotationContainerConfigurationFactory<C>()
                ),
                new AnnotationPortSetter(),
                new ShutdownRunListenerFactory(),
                container,
                test
        );
    }
}
