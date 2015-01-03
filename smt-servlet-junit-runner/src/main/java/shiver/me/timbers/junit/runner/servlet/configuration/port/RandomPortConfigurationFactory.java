package shiver.me.timbers.junit.runner.servlet.configuration.port;

import shiver.me.timbers.junit.runner.servlet.EmptyFactory;

/**
 * Returns a {@link PortConfiguration} that will set a random free port.
 *
 * @author Karl Bennett
 */
public interface RandomPortConfigurationFactory extends EmptyFactory<PortConfiguration> {
}
