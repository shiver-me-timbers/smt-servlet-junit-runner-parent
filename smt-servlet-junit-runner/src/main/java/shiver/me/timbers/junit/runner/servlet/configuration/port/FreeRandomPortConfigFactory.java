package shiver.me.timbers.junit.runner.servlet.configuration.port;

/**
 * @author Karl Bennett
 */
public class FreeRandomPortConfigFactory implements RandomPortConfigFactory {

    @Override
    public PortConfiguration create() {
        return new SocketPortConfiguration();
    }
}
