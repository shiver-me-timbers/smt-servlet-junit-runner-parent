package shiver.me.timbers.junit.runner;

/**
 * This interface provides a generic interface for starting, stopping, and configuring specific containers.
 *
 * @author Karl Bennett
 */
public interface Container<C> {

    void config(PortConfig portConfig);

    void config(ContainerConfig<C> containerConfig);

    void start();

    void load(Servlets servlets);
}
