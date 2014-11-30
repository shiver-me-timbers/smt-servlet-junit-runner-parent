package shiver.me.timbers.junit.runner;

import shiver.me.timbers.junit.runner.config.SocketConfig;

/**
 * This interface should be implemented to expose the port to the test instance.
 *
 * @author Karl Bennett
 */
public interface PortSetter {

    void set(Object target, SocketConfig socketConfig);
}
