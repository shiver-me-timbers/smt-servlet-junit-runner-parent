package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

import javax.servlet.Filter;

/**
 * @author Karl Bennett
 */
public class FiltersAnnotationFactory implements AnnotationFactory<ContainerConfiguration, Filters> {

    private final Logger log = LoggerFactory.getLogger(FiltersAnnotationFactory.class);

    @Override
    public Filters create(ContainerConfiguration configuration) {

        final Class<? extends Filter>[] filters = configuration.filters();

        log.debug("Created filters from {}", filters);
        return new SettableFilters(filters);
    }
}
