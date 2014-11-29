package shiver.me.timbers.junit.runner;

/**
 * This factory will find any port configuration on the test class that will set the port of the servlet container.
 *
 * @author Karl Bennett
 */
public interface PortFactory {

    PortConfig create(Object target);
}
