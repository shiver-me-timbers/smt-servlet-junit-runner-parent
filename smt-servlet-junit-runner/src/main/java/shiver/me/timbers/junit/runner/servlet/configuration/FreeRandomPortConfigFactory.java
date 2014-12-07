package shiver.me.timbers.junit.runner.servlet.configuration;

/**
 * @author Karl Bennett
 */
public class FreeRandomPortConfigFactory implements RandomPortConfigFactory {

    @Override
    public PortConfiguration create() {
        return new SocketPortConfiguration();
    }
}
