package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;

import javax.servlet.Filter;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class FilterFiltersFactory implements Factory<List<Class<? extends Filter>>, Filters> {

    private final Logger log = LoggerFactory.getLogger(FilterFiltersFactory.class);

    @SuppressWarnings("unchecked")
    @Override
    public Filters create(List<Class<? extends Filter>> types) {
        log.debug("Created filters from {}", types);
        return new SettableFilters(types.toArray(new Class[types.size()]));
    }
}
