package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.EmptyFactory;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;

import java.util.ArrayList;

/**
 * @author Karl Bennett
 */
public class FiltersEmptyFactory implements EmptyFactory<Filters> {

    private final Logger log = LoggerFactory.getLogger(FiltersEmptyFactory.class);

    @Override
    public Filters create() {
        log.debug("No filters created.");
        return new SettableFilters(new ArrayList<FilterDetail>());
    }
}
