package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(ConfiguredFiltersAnnotationFactory.class);

    @Override
    public Filters create(ContainerConfiguration annotation) {

        final FilterConfiguration[] configurations = annotation.filterConfigurations();

        log.debug("Adding configured filters");
        final List<FilterDetail> filterDetails = new ArrayList<>();
        for (FilterConfiguration configuration : configurations) {
            log.debug("Adding {}", configuration);
            filterDetails.add(new FilterDetail(configuration.servlet(), configuration.configuration()));
        }
        log.debug("Added {}", filterDetails);
        return new SettableFilters(filterDetails);
    }
}
