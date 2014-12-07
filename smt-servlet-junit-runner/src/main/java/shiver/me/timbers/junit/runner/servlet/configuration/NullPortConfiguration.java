package shiver.me.timbers.junit.runner.servlet.configuration;

/**
 * @author Karl Bennett
 */
public class NullPortConfiguration implements PortConfiguration {

    public static final NullPortConfiguration NULL_PORT_CONFIG = new NullPortConfiguration();

    private NullPortConfiguration() {
    }

    @Override
    public int getPort() {
        return -1;
    }
}
