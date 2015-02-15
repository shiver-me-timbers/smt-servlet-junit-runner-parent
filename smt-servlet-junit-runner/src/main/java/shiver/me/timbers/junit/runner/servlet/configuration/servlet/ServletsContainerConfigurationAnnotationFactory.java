package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationExtractionFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ClassPathsToClassesConverter;
import shiver.me.timbers.junit.runner.servlet.configuration.CompositeContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.PackagesAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ResourceClassPathsFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ResourcePackagesFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.SubTypeFilter;
import shiver.me.timbers.junit.runner.servlet.inject.ClassAnnotationExtractor;

import javax.servlet.Servlet;

/**
 * @author Karl Bennett
 */
public class ServletsContainerConfigurationAnnotationFactory
        extends AnnotationExtractionFactory<ContainerConfiguration, Servlets> implements ServletsFactory {

    private final Logger log = LoggerFactory.getLogger(ServletsContainerConfigurationAnnotationFactory.class);

    public ServletsContainerConfigurationAnnotationFactory() {
        super(
                new ClassAnnotationExtractor<>(ContainerConfiguration.class),
                new ServletsEmptyFactory(),
                new CompositeContainerConfigurationAnnotationFactory<>(
                        new ListServletsFactory(),
                        new ServletsAnnotationFactory(),
                        new PackagesAnnotationFactory<>(
                                new ResourcePackagesFactory<>(
                                        new ResourceClassPathsFactory(),
                                        new ClassPathsToClassesConverter(),
                                        new SubTypeFilter<>(Servlet.class),
                                        new ServletServletsFactory()
                                )
                        ),
                        new ConfiguredServletsAnnotationFactory()
                )
        );

        log.debug("Constructed");
    }
}
