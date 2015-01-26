package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationExtractionFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ClassPathsToClassesConverter;
import shiver.me.timbers.junit.runner.servlet.configuration.CompositeContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.PackagesAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ResourceClassPathsFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ResourcePackagesFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.SubTypeFilter;

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
                                new ResourcePackagesFactory<>(
                                        new ResourceClassPathsFactory(),
                                        new ClassPathsToClassesConverter(),
                                        new SubTypeFilter<>(Filter.class),
                                        new FilterFiltersFactory()
                                )
                        ),
                        new ConfiguredFiltersAnnotationFactory()
                )
        );
    }
}
