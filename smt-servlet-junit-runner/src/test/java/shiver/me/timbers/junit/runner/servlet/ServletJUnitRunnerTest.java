package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfigFactory;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.config.PortConfig;
import shiver.me.timbers.junit.runner.servlet.config.PortConfigFactory;

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

        final PortConfig portConfig = mock(PortConfig.class);
        final PortConfigFactory portConfigFactory = mock(PortConfigFactory.class);
        when(portConfigFactory.create(any(TestClass.class))).thenReturn(portConfig);

        final Servlets servlets = mock(Servlets.class);
        final ServletsFactory servletsFactory = mock(ServletsFactory.class);
        when(servletsFactory.create(any(TestClass.class))).thenReturn(servlets);

        @SuppressWarnings("unchecked")
        final ContainerConfiguration<Object> containerConfiguration = mock(ContainerConfiguration.class);
        @SuppressWarnings("unchecked")
        final ContainerConfigFactory<Object> containerConfigFactory = mock(ContainerConfigFactory.class);
        when(containerConfigFactory.create(any(TestClass.class))).thenReturn(containerConfiguration);

        final RunListener runListener = mock(RunListener.class);
        final RunListenerFactory runListenerFactory = mock(RunListenerFactory.class);
        when(runListenerFactory.create(container)).thenReturn(runListener);

        final PortSetter portSetter = mock(PortSetter.class);

        // When
        new ServletJUnitRunner<>(
                portConfigFactory,
                servletsFactory,
                containerConfigFactory,
                portSetter,
                runListenerFactory,
                container,
                test
        ).run(notifier);

        // Then
        verify(container).config(portConfig);
        verify(container).config(containerConfiguration);
        verify(container).load(servlets);
        verify(container).start();
        verify(portSetter).set(any(TestClass.class), eq(portConfig));
        verify(runListenerFactory).create(container);
        verify(notifier).addListener(runListener);
    }

    public static class TestClass {

        @Test
        public void a_test_test() {
        }
    }
}
