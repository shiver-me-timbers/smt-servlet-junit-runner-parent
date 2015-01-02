package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;
import shiver.me.timbers.junit.runner.servlet.configuration.EmptyFactory;

import java.util.ArrayList;

/**
 * @author Karl Bennett
 */
public class FiltersEmptyFactory implements EmptyFactory<Filters> {

    @Override
    public Filters create() {

        return new SettableFilters(new ArrayList<FilterDetail>());
    }
}
