package shiver.me.timbers.junit.runner.servlet;

import java.util.List;

/**
 * @author Karl Bennett
 */
public interface Servlets extends Iterable<ServletDetail> {

    List<ServletDetail> asList();

    void add(Servlets servlets);
}
