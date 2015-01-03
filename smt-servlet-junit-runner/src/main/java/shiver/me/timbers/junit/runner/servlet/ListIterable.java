package shiver.me.timbers.junit.runner.servlet;

import java.util.List;

/**
 * @author Karl Bennett
 */
public interface ListIterable<T, E> extends Iterable<E> {

    List<E> asList();

    void add(T type);
}
