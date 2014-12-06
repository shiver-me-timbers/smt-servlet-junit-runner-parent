package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.config.PortConfig;

public class TestServletContainer implements Container {

    private PortConfig portConfig;
    private ContainerConfiguration containerConfiguration;
    private Servlets servlets;
    private boolean started = false;

    @Override
    public void config(PortConfig portConfig) {
        this.portConfig = portConfig;
    }

    public int getPort() {
        return portConfig.getPort();
    }

    @Override
    public void config(ContainerConfiguration containerConfiguration) {
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
