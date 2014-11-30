package shiver.me.timbers.junit.runner;

import org.junit.Test;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.config.ContainerConfig;
import shiver.me.timbers.junit.runner.config.ContainerConfigFactory;
import shiver.me.timbers.junit.runner.config.PortConfig;
import shiver.me.timbers.junit.runner.config.PortConfigFactory;

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
        final ContainerConfig<Object> containerConfig = mock(ContainerConfig.class);
        @SuppressWarnings("unchecked")
        final ContainerConfigFactory<Object> containerConfigFactory = mock(ContainerConfigFactory.class);
        when(containerConfigFactory.create(any(TestClass.class))).thenReturn(containerConfig);

        final RunListener runListener = mock(RunListener.class);
        final RunListenerFactory runListenerFactory = mock(RunListenerFactory.class);
        when(runListenerFactory.create(container)).thenReturn(runListener);

        final PortSetter portSetter = mock(PortSetter.class);

        // When
        new ServletJUnitRunner<>(
                container,
                portConfigFactory,
                servletsFactory,
                containerConfigFactory,
                runListenerFactory,
                test,
                portSetter
        ).run(notifier);

        // Then
        verify(container).config(portConfig);
        verify(container).config(containerConfig);
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
