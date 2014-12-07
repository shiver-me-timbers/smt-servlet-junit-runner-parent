package shiver.me.timbers.junit.runner.servlet.configuration;

/**
 * @author Karl Bennett
 */
public class NullContainerConfiguration<C> implements ContainerConfiguration<C> {

    public static final ContainerConfiguration NULL_CONTAINER_CONFIG = new NullContainerConfiguration();

    private NullContainerConfiguration() {
    }

    @Override
    public void configure(C container) {
    }
}
