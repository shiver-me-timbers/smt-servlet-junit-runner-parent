package shiver.me.timbers.junit.runner.config;

/**
 * This factory will find any custom container configuration that has been set on the test and return it ready to be
 * applied to the servlet container before it is started.
 *
 * @author Karl Bennett
 */
public interface ContainerConfigFactory<C> {

    ContainerConfig<C> create(Object target);
}
