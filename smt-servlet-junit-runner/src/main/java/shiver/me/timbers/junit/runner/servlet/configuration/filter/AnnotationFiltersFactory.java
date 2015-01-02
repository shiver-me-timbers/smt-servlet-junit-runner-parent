package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.util.ArrayList;

/**
 * @author Karl Bennett
 */
public class AnnotationFiltersFactory implements FiltersFactory {

    @Override
    public Filters create(Object target) {

        final Class<?> type = target.getClass();

        final ContainerConfiguration configuration = type.getAnnotation(ContainerConfiguration.class);

        if (null == configuration) {
            return new SettableFilters(new ArrayList<FilterDetail>());
        }

        return new SettableFilters(configuration.filters());
    }
}
