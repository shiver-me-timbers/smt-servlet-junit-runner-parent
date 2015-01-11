package shiver.me.timbers.junit.runner.servlet;

import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.configuration.ClassAnnotationContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.CompositeContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.MethodAnnotationContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.WebXmlContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.filter.FiltersContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.AnnotationStaticPortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.FreeRandomPortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.SettablePortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.servlet.ServletsContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationPortSetter;

/**
 * This can be extended to create a new container specific implementation the {@link ServletJUnitRunner}.
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
                new WebXmlContainerConfigurationAnnotationFactory(),
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
