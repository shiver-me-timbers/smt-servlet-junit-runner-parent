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

import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.AnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;

import java.net.URL;

public class TestAnnotationServletJUnitRunner extends AnnotationServletJUnitRunner<TestServletContainer> {

    public TestAnnotationServletJUnitRunner(Class test) throws InitializationError {
        super(
                new Container<TestServletContainer>() {

                    private final TestServletContainer container = new TestServletContainer();

                    @Override
                    public void configure(PortConfiguration portConfiguration) {
                        container.configure(portConfiguration);
                    }

                    @Override
                    public void configure(ContainerConfiguration<TestServletContainer> containerConfiguration) {
                        container.configure(containerConfiguration);
                        containerConfiguration.configure(container);
                    }

                    @Override
                    public void start() {
                        container.start();
                    }

                    @Override
                    public void load(Servlets servlets) {
                        container.load(servlets);
                    }

                    @Override
                    public void load(Filters filters) {
                        container.load(filters);
                    }

                    @Override
                    public void load(URL webXml) {
                        container.load(webXml);
                    }

                    @Override
                    public void shutdown() {
                        container.shutdown();
                    }
                },
                test
        );
    }
}
