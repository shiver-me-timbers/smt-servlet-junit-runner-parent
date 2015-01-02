package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationExtractionFactory;

/**
 * @author Karl Bennett
 */
public class FiltersContainerConfigurationAnnotationFactory
        extends AnnotationExtractionFactory<ContainerConfiguration, Filters> implements FiltersFactory {

    public FiltersContainerConfigurationAnnotationFactory() {
        super(ContainerConfiguration.class, new FiltersEmptyFactory(), new FiltersAnnotationFactory());
    }
}
