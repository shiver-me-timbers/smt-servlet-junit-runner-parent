package shiver.me.timbers.junit.runner.servlet.config;

/**
 * Returns a {@link PortConfiguration} that will set a random free port.
 *
 * @author Karl Bennett
 */
public interface RandomPortConfigFactory {

    PortConfiguration create();
}
