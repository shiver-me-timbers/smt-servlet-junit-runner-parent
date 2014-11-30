package shiver.me.timbers.junit.runner.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfig;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.annotation.Servlets;
import shiver.me.timbers.junit.runner.servlet.config.PortConfig;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.SERVLETS;

public class AnnotationServletJUnitRunnerTest {

    private static final int PORT = 9997;

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

        // When
        new AnnotationServletJUnitRunner<>(new TestContainer(), ClassLevelConfig.class).run(notifier);
        notifier.fireTestRunFinished(mock(Result.class));

        // Then
        verify(server).configured();
        verify(server).configuredPort(PORT);
        verify(server).load(SERVLETS);
        verify(server).start();
        verify(server).injectedPort(PORT);
        verify(server).shutdown();
    }

    @Test
    public void A_test_can_the_server_configuration_with_a_method_and_a_random_port_injected()
            throws InitializationError {

        // Given
        final RunNotifier notifier = new RunNotifier();

        // When
        new AnnotationServletJUnitRunner<>(new TestContainer(), MethodLevelConfig.class).run(notifier);
        notifier.fireTestRunFinished(mock(Result.class));

        // Then
        verify(server).configured();
        verify(server).configuredPort(intThat(greaterThan(0)));
        verify(server).load(new ArrayList<Class<? extends Servlet>>());
        verify(server).start();
        verify(server).injectedPort(intThat(greaterThan(0)));
        verify(server).shutdown();
    }

    @Port(PORT)
    @ContainerConfig(TestContainerConfig.class)
    @Servlets({ServletOne.class, ServletTwo.class, ServletThree.class})
    public static class ClassLevelConfig {

        @Port
        private int port;

        @Test
        public void test() {
            REFERENCE.get().injectedPort(port);
        }
    }

    public static class MethodLevelConfig {

        @ContainerConfig
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

    public static class TestContainerConfig implements
            shiver.me.timbers.junit.runner.servlet.config.ContainerConfig<TestServletContainer> {

        @Override
        public void configure(TestServletContainer server) {
            server.configured();
        }
    }

    public class TestContainer implements Container<TestServletContainer> {

        @Override
        public void config(PortConfig portConfig) {
            server.configuredPort(portConfig.getPort());
        }

        @SuppressWarnings("unchecked")
        @Override
        public void config(
                shiver.me.timbers.junit.runner.servlet.config.ContainerConfig<TestServletContainer> containerConfig) {
            containerConfig.configure(server);
        }

        @Override
        public void load(shiver.me.timbers.junit.runner.servlet.Servlets servlets) {
            server.load(servlets.getServlets());
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

        void load(List<Class<? extends Servlet>> servlets);

        void shutdown();
    }
}
