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

package shiver.me.timbers.junit.runner.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.annotation.FilterConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.annotation.ServletConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PORT;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.WEB_XML_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.WEB_XML_URL;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.CONFIGURED_FILTER_DETAIL_ONE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.CONFIGURED_FILTER_DETAIL_ONE_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.CONFIGURED_FILTER_DETAIL_THREE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.CONFIGURED_FILTER_DETAIL_THREE_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.mockAllFilters;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.mockEmptyFilters;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_ONE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_THREE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_TWO;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.CONFIGURED_SERVLET_DETAIL_ONE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.CONFIGURED_SERVLET_DETAIL_ONE_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.CONFIGURED_SERVLET_DETAIL_TWO_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.CONFIGURED_SERVLET_DETAIL_TWO_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.mockAllServlets;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.mockEmptyServlets;

public class AnnotationServletJUnitRunnerTest {

    private static final AtomicReference<TestServletContainer> REFERENCE = new AtomicReference<>();

    private TestServletContainer server;

    @Before
    public void setUp() {
        server = mock(TestServletContainer.class);

        REFERENCE.set(server);
    }

    @Test
    public void A_test_can_have_everything_configured_at_the_class_level_and_the_port_injected()
            throws InitializationError {

        // Given
        final RunNotifier notifier = new RunNotifier();
        final Servlets servlets = mockAllServlets();
        final Filters filters = mockAllFilters();

        // When
        new AnnotationServletJUnitRunner<>(new TestContainer(), ClassLevelConfig.class).run(notifier);
        notifier.fireTestRunFinished(mock(Result.class));

        // Then
        verify(server).configured();
        verify(server).configuredPort(PORT);
        verify(server).load(argThat(equalAll(servlets)));
        verify(server).load(argThat(equalAll(filters)));
        verify(server).load(WEB_XML_URL);
        verify(server).start();
        verify(server).injectedPort(PORT);
        verify(server).shutdown();
    }

    @Test
    public void A_test_can_the_server_configuration_with_a_method_and_a_random_port_injected()
            throws InitializationError {

        // Given
        final RunNotifier notifier = new RunNotifier();
        final Servlets servlets = mockEmptyServlets();
        final Filters filters = mockEmptyFilters();

        // When
        new AnnotationServletJUnitRunner<>(new TestContainer(), MethodLevelConfig.class).run(notifier);
        notifier.fireTestRunFinished(mock(Result.class));

        // Then
        verify(server).configured();
        verify(server).configuredPort(intThat(greaterThan(0)));
        verify(server).load(argThat(equalAll(servlets)));
        verify(server).load(argThat(equalAll(filters)));
        verify(server, never()).load(any(URL.class));
        verify(server).start();
        verify(server).injectedPort(intThat(greaterThan(0)));
        verify(server).shutdown();
    }

    @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration(
            port = PORT,
            value = TestContainerConfiguration.class,
            servlets = {ServletOne.class, ServletTwo.class, ServletThree.class},
            servletConfigurations = {
                    @ServletConfiguration(
                            configuration = @WebServlet(
                                    name = CONFIGURED_SERVLET_DETAIL_ONE_NAME,
                                    value = CONFIGURED_SERVLET_DETAIL_ONE_PATH
                            ),
                            servlet = ServletOne.class
                    ),
                    @ServletConfiguration(
                            configuration = @WebServlet(
                                    name = CONFIGURED_SERVLET_DETAIL_TWO_NAME,
                                    value = CONFIGURED_SERVLET_DETAIL_TWO_PATH
                            ),
                            servlet = ServletTwo.class
                    )
            },
            filterConfigurations = {
                    @FilterConfiguration(
                            configuration = @WebFilter(
                                    filterName = CONFIGURED_FILTER_DETAIL_ONE_NAME,
                                    value = CONFIGURED_FILTER_DETAIL_ONE_PATH
                            ),
                            servlet = FilterOne.class
                    ),
                    @FilterConfiguration(
                            configuration = @WebFilter(
                                    filterName = CONFIGURED_FILTER_DETAIL_THREE_NAME,
                                    value = CONFIGURED_FILTER_DETAIL_THREE_PATH
                            ),
                            servlet = FilterTwo.class
                    )
            },
            filters = {FilterOne.class, FilterTwo.class, FilterThree.class},
            packages = {PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE},
            webXml = WEB_XML_PATH
    )
    public static class ClassLevelConfig {

        @Port
        private int port;

        @Test
        public void test() {
            REFERENCE.get().injectedPort(port);
        }
    }

    public static class MethodLevelConfig {

        @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration
        public void config(TestServletContainer server) {
            server.configured();
        }

        @Port
        private Integer port;

        @Test
        public void test() {
            REFERENCE.get().injectedPort(port);
        }
    }

    public static class TestContainerConfiguration implements
            ContainerConfiguration<TestServletContainer> {

        @Override
        public void configure(TestServletContainer server) {
            server.configured();
        }
    }

    public class TestContainer implements Container<TestServletContainer> {

        @Override
        public void configure(PortConfiguration portConfiguration) {
            server.configuredPort(portConfiguration.getPort());
        }

        @SuppressWarnings("unchecked")
        @Override
        public void configure(
                ContainerConfiguration<TestServletContainer> containerConfiguration) {
            containerConfiguration.configure(server);
        }

        @Override
        public void load(Servlets servlets) {
            server.load(servlets);
        }

        @Override
        public void load(Filters filters) {
            server.load(filters);
        }

        @Override
        public void load(URL webXml) {
            server.load(webXml);
        }

        @Override
        public void start() {
            server.start();
        }

        @Override
        public void shutdown() {
            server.shutdown();
        }
    }

    private static interface TestServletContainer {

        void configured();

        void configuredPort(Integer port);

        void injectedPort(Integer port);

        void start();

        void load(Servlets servlets);

        void load(Filters filters);

        void load(URL webXml);

        void shutdown();
    }
}
