package shiver.me.timbers.junit.runner.config;

/**
 * This interface should be implemented to configure the servlet server before it is started.
 *
 * @author Karl Bennett
 */
public interface ContainerConfig<C> {

    void configure(C container);
}
