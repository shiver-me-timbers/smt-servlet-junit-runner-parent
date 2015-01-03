package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationExtractionFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.CompositeContainerConfigurationAnnotationFactory;

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
                        new ServletsPackagesAnnotationFactory(new ReflectionsPackagesServletsFactory())
                )
        );
    }
}
