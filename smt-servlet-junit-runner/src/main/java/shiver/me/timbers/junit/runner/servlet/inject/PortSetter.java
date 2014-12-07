package shiver.me.timbers.junit.runner.servlet.inject;

import shiver.me.timbers.junit.runner.servlet.config.PortConfiguration;

/**
 * This interface should be implemented to expose the port to the test instance.
 *
 * @author Karl Bennett
 */
public interface PortSetter {

    void set(Object target, PortConfiguration portConfiguration);
}
