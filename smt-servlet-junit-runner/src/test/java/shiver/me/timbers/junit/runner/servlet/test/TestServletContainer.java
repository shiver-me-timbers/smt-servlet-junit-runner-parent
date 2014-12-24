package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.PortConfiguration;

public class TestServletContainer implements Container {

    private PortConfiguration portConfiguration;
    private ContainerConfiguration containerConfiguration;
    private Servlets servlets;
    private Filters filters;
    private boolean started = false;

    @Override
    public void configure(PortConfiguration portConfiguration) {
        this.portConfiguration = portConfiguration;
    }

    public int getPort() {
        return portConfiguration.getPort();
    }

    @Override
    public void configure(ContainerConfiguration containerConfiguration) {
        this.containerConfiguration = containerConfiguration;
    }

    public ContainerConfiguration getContainerConfiguration() {
        return containerConfiguration;
    }

    @Override
    public void load(Servlets servlets) {
        this.servlets = servlets;
    }

    public Servlets getServlets() {
        return servlets;
    }

    @Override
    public void load(Filters filters) {
        this.filters = filters;
    }

    public Filters getFilters() {
        return filters;
    }

    @Override
    public void start() {
        started = true;
    }

    public boolean isStarted() {
        return started;
    }

    @Override
    public void shutdown() {
    }
}
