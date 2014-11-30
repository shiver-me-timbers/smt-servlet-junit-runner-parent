package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfig;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.test.TestAnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.servlet.test.TestServletContainer;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(TestAnnotationServletJUnitRunner.class)
public class MethodLevelAnnotationServletJUnitRunnerTest {

    private TestServletContainer container;

    @ContainerConfig
    public void config(TestServletContainer container) {
        this.container = container;
    }

    @Port
    private int port;

    @Test
    public void Configuration_at_the_class_level_should_work() {

        // Then
        assertThat(port, greaterThan(0));
        assertEquals(container.getPort(), port);
        assertThat(container.getServlets(), empty());
        assertTrue(container.isStarted());
    }
}
