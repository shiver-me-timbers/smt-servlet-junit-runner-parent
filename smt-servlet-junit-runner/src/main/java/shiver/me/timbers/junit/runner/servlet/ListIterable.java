package shiver.me.timbers.junit.runner.servlet;

import java.util.List;

/**
 * @author Karl Bennett
 */
public interface ListIterable<T> extends Iterable<T> {
    List<T> asList();
}
