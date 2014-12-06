package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.TestAnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.servlet.test.TestContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.TestServletContainer;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockServlets;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;
import static shiver.me.timbers.junit.runner.servlet.test.TestContainerConfiguration.TEST_SERVLET_CONTAINER_REFERENCE;

@RunWith(TestAnnotationServletJUnitRunner.class)
@Port(9996)
@ContainerConfiguration(
        value = TestContainerConfiguration.class,
        servlets = {ServletOne.class, ServletTwo.class, ServletThree.class}
)
public class ClassLevelAnnotationServletJUnitRunnerTest {

    private static final int PORT = 9996;

    @Port
    private int port;

    @Test
    public void Configuration_at_the_class_level_should_work() {

        // Given
        final TestServletContainer container = TEST_SERVLET_CONTAINER_REFERENCE.get();
        final Servlets servlets = mockServlets();

        // Then
        assertEquals(PORT, port);
        assertEquals(PORT, container.getPort());
        assertThat((TestContainerConfiguration) container.getContainerConfiguration(),
                isA(TestContainerConfiguration.class));
        assertThat(container.getServlets(), equalTo(servlets));
        assertTrue(container.isStarted());
    }
}
