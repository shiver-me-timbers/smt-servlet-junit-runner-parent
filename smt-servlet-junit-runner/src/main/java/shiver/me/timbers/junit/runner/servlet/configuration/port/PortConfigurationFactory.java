package shiver.me.timbers.junit.runner.servlet.configuration.port;

import shiver.me.timbers.junit.runner.servlet.Factory;

/**
 * This factory will find any port configuration on the test class that will be used to set the socket of the servlet
 * container.
 *
 * @author Karl Bennett
 */
public interface PortConfigurationFactory extends Factory<Object, PortConfiguration> {
}
