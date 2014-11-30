package shiver.me.timbers.junit.runner;

import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.config.AggregatedContainerConfigFactory;
import shiver.me.timbers.junit.runner.config.AnnotationStaticPortConfigFactory;
import shiver.me.timbers.junit.runner.config.ClassAnnotationContainerConfigFactory;
import shiver.me.timbers.junit.runner.config.FreeRandomPortConfigFactory;
import shiver.me.timbers.junit.runner.config.MethodAnnotationContainerConfigFactory;
import shiver.me.timbers.junit.runner.config.SettablePortConfigFactory;

/**
 * Adding this runner to a JUnit class will cause a servlet server to start up before the test.
 *
 * The server will start up on a random free port which can be accessed within the test by annotating an
 * {@code Integer}/{@code int} field with {@link shiver.me.timbers.junit.runner.annotation.Port}. The port number can
 * be set manually by instead annotating the test class with with {@code Port} and setting it's value to the desired
 * port number.
 *
 * By default the started server will scan the package of the test file for any classes annotated with
 * {@link javax.servlet.annotation.WebServlet} which it will then load.
 *
 * Alternatively the {@link shiver.me.timbers.junit.runner.annotation.Servlets} annotation can be used to restrict which
 * servlet classes are loaded.
 *
 * The server can be configured for an individual test class with a method that has the servlet containers configuration
 * object as it's first argument and has been annotated with
 * {@link shiver.me.timbers.junit.runner.annotation.ContainerConfig}. This will cause a new server instance to start up
 * for that specific test class.
 *
 * If the same configuration can be used across multiple test classes then the classes can be annotated with
 * {@code ContainerConfig} that has it's value set to an implementation of
 * {@link shiver.me.timbers.junit.runner.config.ContainerConfig}.
 *
 * @author Karl Bennett
 */
public class AnnotationServletJUnitRunner<C> extends ServletJUnitRunner<C> {

    public AnnotationServletJUnitRunner(Container<C> container, Class test) throws InitializationError {
        super(
                new SettablePortConfigFactory(
                        new AnnotationStaticPortConfigFactory(),
                        new FreeRandomPortConfigFactory()
                ),
                new AnnotationServletsFactory(),
                new AggregatedContainerConfigFactory<>(
                        new ClassAnnotationContainerConfigFactory<C>(),
                        new MethodAnnotationContainerConfigFactory<C>()
                ),
                new AnnotationPortSetter(),
                new ShutdownRunListenerFactory(),
                container,
                test
        );
    }
}
