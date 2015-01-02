package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import shiver.me.timbers.junit.runner.servlet.Filters;

/**
 * This factory will find any servlet configuration that has been set on the test and return it ready to be applied
 * before the servlet container is started.
 *
 * @author Karl Bennett
 */
public interface FiltersFactory {

    Filters create(Object target);
}
