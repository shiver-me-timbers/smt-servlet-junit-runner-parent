package shiver.me.timbers.junit.runner.servlet.configuration;

/**
 * This factory will find any custom container configuration that has been set on the test and return it ready to be
 * applied to the servlet container before it is started.
 *
 * @author Karl Bennett
 */
public interface ContainerConfigurationFactory<C> {

    ContainerConfiguration<C> create(Object target);
}
