package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

/**
 * @author Karl Bennett
 */
public class FiltersAnnotationFactory implements AnnotationFactory<ContainerConfiguration, Filters> {

    @Override
    public Filters create(ContainerConfiguration configuration) {
        return new SettableFilters(configuration.filters());
    }
}
