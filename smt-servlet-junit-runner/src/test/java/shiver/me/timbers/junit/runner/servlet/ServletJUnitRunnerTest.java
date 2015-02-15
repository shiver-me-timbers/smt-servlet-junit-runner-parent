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

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.WebXmlFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.filter.FiltersFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.servlet.ServletsFactory;
import shiver.me.timbers.junit.runner.servlet.inject.PortSetter;

import java.net.URL;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.url;

public class ServletJUnitRunnerTest {

    @Test
    public void The_runner_can_be_run() throws InitializationError {

        // Given
        final RunNotifier notifier = mock(RunNotifier.class);

        @SuppressWarnings("unchecked")
        final Container<Object> container = mock(Container.class);

        final Class<TestClass> test = TestClass.class;

        final PortConfiguration portConfiguration = mock(PortConfiguration.class);
        final PortConfigurationFactory portConfigurationFactory = mock(PortConfigurationFactory.class);
        when(portConfigurationFactory.create(any(TestClass.class))).thenReturn(portConfiguration);

        @SuppressWarnings("unchecked")
        final ContainerConfiguration<Object> containerConfiguration = mock(ContainerConfiguration.class);
        @SuppressWarnings("unchecked")
        final ContainerConfigurationFactory<Object> containerConfigurationFactory = mock(ContainerConfigurationFactory.class);
        when(containerConfigurationFactory.create(any(TestClass.class))).thenReturn(containerConfiguration);

        final Servlets servlets = mock(Servlets.class);
        final ServletsFactory servletsFactory = mock(ServletsFactory.class);
        when(servletsFactory.create(any(TestClass.class))).thenReturn(servlets);

        final Filters filters = mock(Filters.class);
        final FiltersFactory filtersFactory = mock(FiltersFactory.class);
        when(filtersFactory.create(any(TestClass.class))).thenReturn(filters);

        final URL url = url();
        final WebXmlFactory webXmlFactory = mock(WebXmlFactory.class);
        when(webXmlFactory.create(any(TestClass.class))).thenReturn(url);

        final RunListener runListener = mock(RunListener.class);
        final RunListenerFactory runListenerFactory = mock(RunListenerFactory.class);
        when(runListenerFactory.create(container)).thenReturn(runListener);

        final PortSetter portSetter = mock(PortSetter.class);

        // When
        new ServletJUnitRunner<>(
                portConfigurationFactory,
                servletsFactory,
                filtersFactory,
                webXmlFactory,
                containerConfigurationFactory,
                portSetter,
                runListenerFactory,
                container,
                test
        ).run(notifier);

        // Then
        verify(container).configure(portConfiguration);
        verify(container).configure(containerConfiguration);
        verify(container).load(servlets);
        verify(container).load(filters);
        verify(container).load(url);
        verify(container).start();
        verify(portSetter).set(any(TestClass.class), eq(portConfiguration));
        verify(runListenerFactory).create(container);
        verify(notifier).addListener(runListener);
        verify(notifier).fireTestStarted(any(Description.class));
        verify(notifier).fireTestFinished(any(Description.class));

        verifyNoMoreInteractions(container, portSetter, runListenerFactory, notifier);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void Configuration_should_only_carried_out_when_necessary() throws InitializationError {

        // Given
        final RunNotifier notifier = mock(RunNotifier.class);

        final Container<Object> container = mock(Container.class);

        // When
        new ServletJUnitRunner<>(
                mock(PortConfigurationFactory.class),
                mock(ServletsFactory.class),
                mock(FiltersFactory.class),
                mock(WebXmlFactory.class),
                mock(ContainerConfigurationFactory.class),
                mock(PortSetter.class),
                mock(RunListenerFactory.class),
                container,
                TestClass.class
        ).run(notifier);

        // Then
        verify(container, never()).load(any(URL.class));
    }

    public static class TestClass {

        @Test
        public void a_test_test() {
        }
    }
}
