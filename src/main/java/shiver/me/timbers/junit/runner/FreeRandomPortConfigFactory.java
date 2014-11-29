package shiver.me.timbers.junit.runner;

/**
 * @author Karl Bennett
 */
public class FreeRandomPortConfigFactory implements RandomPortConfigFactory {

    @Override
    public SocketConfig create() {
        return new SettableSocketConfig();
    }
}
