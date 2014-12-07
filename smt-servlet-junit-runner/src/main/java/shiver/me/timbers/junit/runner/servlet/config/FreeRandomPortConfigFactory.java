package shiver.me.timbers.junit.runner.servlet.config;

/**
 * @author Karl Bennett
 */
public class FreeRandomPortConfigFactory implements RandomPortConfigFactory {

    @Override
    public PortConfiguration create() {
        return new SocketPortConfiguration();
    }
}
