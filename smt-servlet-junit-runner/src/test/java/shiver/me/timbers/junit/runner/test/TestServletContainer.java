package shiver.me.timbers.junit.runner.test;

import shiver.me.timbers.junit.runner.Container;
import shiver.me.timbers.junit.runner.Servlets;
import shiver.me.timbers.junit.runner.config.ContainerConfig;
import shiver.me.timbers.junit.runner.config.PortConfig;

import javax.servlet.Servlet;
import java.util.List;

public class TestServletContainer implements Container {

    private PortConfig portConfig;
    private ContainerConfig containerConfig;
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
    public void config(ContainerConfig containerConfig) {
        this.containerConfig = containerConfig;
    }

    public ContainerConfig getContainerConfig() {
        return containerConfig;
    }

    @Override
    public void load(Servlets servlets) {
        this.servlets = servlets;
    }

    public List<Class<? extends Servlet>> getServlets() {
        return servlets.getServlets();
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
