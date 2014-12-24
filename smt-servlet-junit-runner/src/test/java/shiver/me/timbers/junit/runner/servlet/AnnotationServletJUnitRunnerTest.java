package shiver.me.timbers.junit.runner.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.PortConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.*;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.*;
import static shiver.me.timbers.junit.runner.servlet.test.FiltersMatcher.equalTo;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;

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
        final Servlets servlets = mockAllServlets();
        final Filters filters = mockAllFilters();

        // When
        new AnnotationServletJUnitRunner<>(new TestContainer(), ClassLevelConfig.class).run(notifier);
        notifier.fireTestRunFinished(mock(Result.class));

        // Then
        verify(server).configured();
        verify(server).configuredPort(PORT);
        verify(server).load(argThat(equalTo(servlets)));
        verify(server).load(argThat(equalTo(filters)));
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
        verify(server).load(argThat(equalTo(servlets)));
        verify(server).load(argThat(equalTo(filters)));
        verify(server).start();
        verify(server).injectedPort(intThat(greaterThan(0)));
        verify(server).shutdown();
    }

    @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration(
            port = PORT,
            value = TestContainerConfiguration.class,
            servlets = {ServletOne.class, ServletTwo.class, ServletThree.class},
            filters = {FilterOne.class, FilterTwo.class, FilterThree.class},
            packages = {PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE}
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

        void shutdown();

        void load(Filters filters);
    }
}
