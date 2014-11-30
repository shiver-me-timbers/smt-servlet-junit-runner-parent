package shiver.me.timbers.junit.runner;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.annotation.ContainerConfig;
import shiver.me.timbers.junit.runner.annotation.Port;
import shiver.me.timbers.junit.runner.annotation.Servlets;
import shiver.me.timbers.junit.runner.test.ServletOne;
import shiver.me.timbers.junit.runner.test.ServletThree;
import shiver.me.timbers.junit.runner.test.ServletTwo;
import shiver.me.timbers.junit.runner.test.TestAnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.test.TestContainerConfig;
import shiver.me.timbers.junit.runner.test.TestServletContainer;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.test.Constants.SERVLETS;
import static shiver.me.timbers.junit.runner.test.TestContainerConfig.TEST_SERVLET_CONTAINER_REFERENCE;

@RunWith(TestAnnotationServletJUnitRunner.class)
@Port(9996)
@ContainerConfig(TestContainerConfig.class)
@Servlets({ServletOne.class, ServletTwo.class, ServletThree.class})
public class ClassLevelAnnotationServletJUnitRunnerTest {

    private static final int PORT = 9996;

    @Port
    private int port;

    @Test
    public void Configuration_at_the_class_level_should_work() {

        // Given
        final TestServletContainer container = TEST_SERVLET_CONTAINER_REFERENCE.get();

        // Then
        assertEquals(PORT, port);
        assertEquals(PORT, container.getPort());
        assertThat((TestContainerConfig) container.getContainerConfig(), isA(TestContainerConfig.class));
        assertEquals(SERVLETS, container.getServlets());
        assertTrue(container.isStarted());
    }
}
