package shiver.me.timbers.junit.runner.servlet;

/**
 * @author Karl Bennett
 */
public interface Servlets extends ListIterable<ServletDetail> {

    void add(Servlets servlets);
}
