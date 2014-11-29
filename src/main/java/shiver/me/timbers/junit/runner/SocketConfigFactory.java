package shiver.me.timbers.junit.runner;

/**
 * This factory will find any port configuration on the test class that will be used to set the socket of the servlet
 * container.
 *
 * @author Karl Bennett
 */
public interface SocketConfigFactory {

    SocketConfig create(Object target);
}
