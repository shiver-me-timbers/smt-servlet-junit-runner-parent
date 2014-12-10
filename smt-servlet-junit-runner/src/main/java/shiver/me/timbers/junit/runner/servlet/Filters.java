package shiver.me.timbers.junit.runner.servlet;

import java.util.List;

/**
 * @author Karl Bennett
 */
public interface Filters extends Iterable<FilterDetail> {

    List<FilterDetail> asList();
}
