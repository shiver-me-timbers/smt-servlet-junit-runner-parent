package shiver.me.timbers.junit.runner;

import shiver.me.timbers.junit.runner.config.ContainerConfig;
import shiver.me.timbers.junit.runner.config.SocketConfig;

/**
 * This interface provides a generic interface for starting, stopping, and configuring specific containers.
 *
 * @author Karl Bennett
 */
public interface Container<C> {

    void config(SocketConfig socketConfig);

    void config(ContainerConfig<C> containerConfig);

    void start();

    void load(Servlets servlets);
}
