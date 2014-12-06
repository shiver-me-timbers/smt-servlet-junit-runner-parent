package shiver.me.timbers.junit.runner.servlet.config;

/**
 * This interface should be implemented to configure the servlet server before it is started.
 *
 * @author Karl Bennett
 */
public interface ContainerConfiguration<C> {

    void configure(C container);
}
