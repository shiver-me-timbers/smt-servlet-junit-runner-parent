package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationExtractionFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.CompositeContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.PackagesAnnotationFactory;

/**
 * @author Karl Bennett
 */
public class ServletsContainerConfigurationAnnotationFactory
        extends AnnotationExtractionFactory<ContainerConfiguration, Servlets> implements ServletsFactory {

    public ServletsContainerConfigurationAnnotationFactory() {
        super(
                ContainerConfiguration.class, new ServletsEmptyFactory(),
                new CompositeContainerConfigurationAnnotationFactory<>(
                        new ServletsListFactory(),
                        new ServletsAnnotationFactory(),
                        new PackagesAnnotationFactory<>(new ReflectionsPackagesServletsFactory())
                )
        );
    }
}
