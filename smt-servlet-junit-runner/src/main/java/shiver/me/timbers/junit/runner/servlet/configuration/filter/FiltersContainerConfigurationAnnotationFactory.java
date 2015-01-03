package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationExtractionFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.CompositeContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.OpenPojoPackagesFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.PackagesAnnotationFactory;

import javax.servlet.Filter;


/**
 * @author Karl Bennett
 */
public class FiltersContainerConfigurationAnnotationFactory
        extends AnnotationExtractionFactory<ContainerConfiguration, Filters> implements FiltersFactory {

    public FiltersContainerConfigurationAnnotationFactory() {
        super(
                ContainerConfiguration.class,
                new FiltersEmptyFactory(),
                new CompositeContainerConfigurationAnnotationFactory<>(
                        new ListFiltersFactory(),
                        new FiltersAnnotationFactory(),
                        new PackagesAnnotationFactory<>(
                                new OpenPojoPackagesFactory<>(
                                        new FilterFiltersFactory(),
                                        Filter.class
                                )
                        )
                )
        );
    }
}
