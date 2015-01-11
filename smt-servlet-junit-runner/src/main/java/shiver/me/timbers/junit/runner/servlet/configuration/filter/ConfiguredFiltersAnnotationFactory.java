package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.FilterConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class ConfiguredFiltersAnnotationFactory implements AnnotationFactory<ContainerConfiguration, Filters> {

    @Override
    public Filters create(ContainerConfiguration annotation) {

        final FilterConfiguration[] configurations = annotation.filterConfigurations();

        final List<FilterDetail> servletDetails = new ArrayList<>();
        for (FilterConfiguration configuration : configurations) {
            servletDetails.add(new FilterDetail(configuration.servlet(), configuration.configuration()));
        }

        return new SettableFilters(servletDetails);
    }
}
