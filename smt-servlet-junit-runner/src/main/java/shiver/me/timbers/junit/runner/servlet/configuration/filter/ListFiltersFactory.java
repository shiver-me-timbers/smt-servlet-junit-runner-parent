package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;

import java.util.List;

/**
 * @author Karl Bennett
 */
public class ListFiltersFactory implements Factory<List<Filters>, Filters> {

    @Override
    public Filters create(List<Filters> filterses) {
        return new SettableFilters(filterses.toArray(new Filters[filterses.size()]));
    }
}
