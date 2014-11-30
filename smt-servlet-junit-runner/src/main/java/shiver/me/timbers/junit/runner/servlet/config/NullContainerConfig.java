package shiver.me.timbers.junit.runner.servlet.config;

/**
 * @author Karl Bennett
 */
public class NullContainerConfig<C> implements ContainerConfig<C> {

    public static final ContainerConfig NULL_CONTAINER_CONFIG = new NullContainerConfig();

    private NullContainerConfig() {
    }

    @Override
    public void configure(C container) {
    }
}
