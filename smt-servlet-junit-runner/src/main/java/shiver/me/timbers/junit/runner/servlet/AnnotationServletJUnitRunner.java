package shiver.me.timbers.junit.runner.servlet;

import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.ClassAnnotationContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ConsecutiveContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.MethodAnnotationContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.WebXmlContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.filter.FiltersContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.AnnotationStaticPortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.FreeRandomPortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.SettablePortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.servlet.ServletsContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationPortSetter;
import shiver.me.timbers.junit.runner.servlet.inject.ClassAnnotationExtractor;

/**
 * This can be extended to create a new container specific implementation the {@link ServletJUnitRunner}.
 *
 * @author Karl Bennett
 */
public class AnnotationServletJUnitRunner<C> extends ServletJUnitRunner<C> {

    private final Logger log = LoggerFactory.getLogger(AnnotationServletJUnitRunner.class);

    private static final ClassAnnotationExtractor<ContainerConfiguration> CLASS_ANNOTATION_EXTRACTOR =
            new ClassAnnotationExtractor<>(ContainerConfiguration.class);

    public AnnotationServletJUnitRunner(Container<C> container, Class test) throws InitializationError {
        super(
                new SettablePortConfigurationFactory(
                        new AnnotationStaticPortConfigurationFactory(CLASS_ANNOTATION_EXTRACTOR),
                        new FreeRandomPortConfigurationFactory()
                ),
                new ServletsContainerConfigurationAnnotationFactory(),
                new FiltersContainerConfigurationAnnotationFactory(),
                new WebXmlContainerConfigurationAnnotationFactory(),
                new ConsecutiveContainerConfigurationFactory<>(
                        new ClassAnnotationContainerConfigurationFactory<C>(CLASS_ANNOTATION_EXTRACTOR),
                        new MethodAnnotationContainerConfigurationFactory<C>()
                ),
                new AnnotationPortSetter(),
                new ShutdownRunListenerFactory(),
                container,
                test
        );

        log.debug("Constructed");
    }
}
