package shiver.me.timbers.junit.runner.config;

/**
 * This factory will find any port configuration on the test class that will be used to set the socket of the servlet
 * container.
 *
 * @author Karl Bennett
 */
public interface PortConfigFactory {

    PortConfig create(Object target);
}
