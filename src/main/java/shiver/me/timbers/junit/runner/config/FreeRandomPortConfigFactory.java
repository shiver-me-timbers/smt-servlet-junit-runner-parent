package shiver.me.timbers.junit.runner.config;

/**
 * @author Karl Bennett
 */
public class FreeRandomPortConfigFactory implements RandomPortConfigFactory {

    @Override
    public PortConfig create() {
        return new SocketPortConfig();
    }
}
