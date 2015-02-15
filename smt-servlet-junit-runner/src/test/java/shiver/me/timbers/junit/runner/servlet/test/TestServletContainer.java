/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;

import java.net.URL;

public class TestServletContainer implements Container {

    private PortConfiguration portConfiguration;
    private ContainerConfiguration containerConfiguration;
    private Servlets servlets;
    private Filters filters;
    private boolean started = false;
    private URL webXml;

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
    public void load(URL webXml) {
        this.webXml = webXml;
    }

    public URL getWebXml() {
        return webXml;
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
