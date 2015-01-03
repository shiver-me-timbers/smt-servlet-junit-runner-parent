package shiver.me.timbers.junit.runner.servlet.configuration.port;

/**
 * @author Karl Bennett
 */
public class FreeRandomPortConfigurationFactory implements RandomPortConfigurationFactory {

    @Override
    public PortConfiguration create() {
        return new SocketPortConfiguration();
    }
}
