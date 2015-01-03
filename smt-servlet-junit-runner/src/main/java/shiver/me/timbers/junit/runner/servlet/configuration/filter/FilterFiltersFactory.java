package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;

import javax.servlet.Filter;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class FilterFiltersFactory implements Factory<List<Class<? extends Filter>>, Filters> {

    @SuppressWarnings("unchecked")
    @Override
    public Filters create(List<Class<? extends Filter>> types) {

        return new SettableFilters(types.toArray(new Class[types.size()]));
    }
}
