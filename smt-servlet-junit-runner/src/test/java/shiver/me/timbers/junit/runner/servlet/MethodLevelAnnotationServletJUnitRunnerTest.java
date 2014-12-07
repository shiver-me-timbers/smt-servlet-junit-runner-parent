package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.test.TestAnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.servlet.test.TestServletContainer;

import java.util.ArrayList;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;

@RunWith(TestAnnotationServletJUnitRunner.class)
public class MethodLevelAnnotationServletJUnitRunnerTest {

    private TestServletContainer container;

    @ContainerConfiguration
    public void config(TestServletContainer container) {
        this.container = container;
    }

    @Port
    private int port;

    @Test
    public void Configuration_at_the_method_level_should_work() {

        // Given
        final Servlets servlets = mock(Servlets.class);
        when(servlets.getServlets()).thenReturn(new ArrayList<ServletDetail>());

        // Then
        assertThat(port, greaterThan(0));
        assertEquals(container.getPort(), port);
        assertThat(container.getServlets(), equalTo(servlets));
        assertTrue(container.isStarted());
    }
}
