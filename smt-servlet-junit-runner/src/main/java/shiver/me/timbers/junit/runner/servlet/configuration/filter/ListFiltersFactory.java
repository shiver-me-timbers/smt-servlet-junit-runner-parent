package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;

import java.util.List;

/**
 * @author Karl Bennett
 */
public class ListFiltersFactory implements Factory<List<Filters>, Filters> {

    private final Logger log = LoggerFactory.getLogger(ListFiltersFactory.class);

    @Override
    public Filters create(List<Filters> filterses) {
        log.debug("Filters created from {}", filterses);
        return new SettableFilters(filterses.toArray(new Filters[filterses.size()]));
    }
}
