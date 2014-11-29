package shiver.me.timbers.junit.runner.config;

/**
 * Returns a {@link SocketConfig} that will set a random free port.
 *
 * @author Karl Bennett
 */
public interface RandomPortConfigFactory {

    SocketConfig create();
}
