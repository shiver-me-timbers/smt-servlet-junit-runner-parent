package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.PortConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.PortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.inject.PortSetter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        final Packages packages = mock(Packages.class);
        final PackagesFactory packagesFactory = mock(PackagesFactory.class);
        when(packagesFactory.create(any(TestClass.class))).thenReturn(packages);

        final RunListener runListener = mock(RunListener.class);
        final RunListenerFactory runListenerFactory = mock(RunListenerFactory.class);
        when(runListenerFactory.create(container)).thenReturn(runListener);

        final PortSetter portSetter = mock(PortSetter.class);

        // When
        new ServletJUnitRunner<>(
                portConfigurationFactory,
                servletsFactory,
                filtersFactory,
                packagesFactory,
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
        verify(container).load(packages);
        verify(container).start();
        verify(portSetter).set(any(TestClass.class), eq(portConfiguration));
        verify(runListenerFactory).create(container);
        verify(notifier).addListener(runListener);
    }

    public static class TestClass {

        @Test
        public void a_test_test() {
        }
    }
}
