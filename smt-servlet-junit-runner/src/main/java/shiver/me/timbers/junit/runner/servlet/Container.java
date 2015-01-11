package shiver.me.timbers.junit.runner.servlet;

import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;

import java.net.URL;

/**
 * This interface provides a generic interface for starting, stopping, and configuring specific containers.
 *
 * @author Karl Bennett
 */
public interface Container<C> {

    void configure(PortConfiguration portConfiguration);

    void configure(ContainerConfiguration<C> containerConfiguration);

    void load(Servlets servlets);

    void load(Filters filters);

    void load(URL webXml);

    void start();

    void shutdown();
}
