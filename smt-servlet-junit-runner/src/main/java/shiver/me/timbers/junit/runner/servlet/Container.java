package shiver.me.timbers.junit.runner.servlet;

import shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.config.PortConfig;

/**
 * This interface provides a generic interface for starting, stopping, and configuring specific containers.
 *
 * @author Karl Bennett
 */
public interface Container<C> {

    void config(PortConfig portConfig);

    void config(ContainerConfiguration<C> containerConfiguration);

    void load(Servlets servlets);

    void start();

    void shutdown();

    void load(Filters filters);
}
