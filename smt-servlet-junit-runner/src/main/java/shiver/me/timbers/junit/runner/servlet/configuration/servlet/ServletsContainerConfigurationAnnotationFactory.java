package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationExtractionFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ClassPathsToClassesConverter;
import shiver.me.timbers.junit.runner.servlet.configuration.CompositeContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.PackagesAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ResourceClassPathsFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ResourcePackagesFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.SubTypeFilter;

import javax.servlet.Servlet;

/**
 * @author Karl Bennett
 */
public class ServletsContainerConfigurationAnnotationFactory
        extends AnnotationExtractionFactory<ContainerConfiguration, Servlets> implements ServletsFactory {

    public ServletsContainerConfigurationAnnotationFactory() {
        super(
                ContainerConfiguration.class,
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
    }
}
