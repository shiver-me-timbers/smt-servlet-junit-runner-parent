package shiver.me.timbers.junit.runner.config;

/**
 * @author Karl Bennett
 */
public class NullPortConfig implements PortConfig {

    public static final NullPortConfig NULL_PORT_CONFIG = new NullPortConfig();

    private NullPortConfig() {
    }

    @Override
    public int getPort() {
        return -1;
    }
}
