package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.test.*;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.*;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.*;
import static shiver.me.timbers.junit.runner.servlet.test.FiltersMatcher.equalTo;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;
import static shiver.me.timbers.junit.runner.servlet.test.TestContainerConfiguration.TEST_SERVLET_CONTAINER_REFERENCE;

@RunWith(TestAnnotationServletJUnitRunner.class)
@ContainerConfiguration(
        port = 9996,
        value = TestContainerConfiguration.class,
        servlets = {ServletOne.class, ServletTwo.class, ServletThree.class},
        filters = {FilterOne.class, FilterTwo.class, FilterThree.class},
        packages = {PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE}
)
public class ClassLevelAnnotationServletJUnitRunnerTest {

    private static final int PORT = 9996;

    @Port
    private int port;

    @Test
    public void Configuration_at_the_class_level_should_work() {

        // Given
        final TestServletContainer container = TEST_SERVLET_CONTAINER_REFERENCE.get();
        final Servlets servlets = mockAllServlets();
        final Filters filters = mockAllFilters();

        // Then
        assertEquals(PORT, port);
        assertEquals(PORT, container.getPort());
        assertThat((TestContainerConfiguration) container.getContainerConfiguration(),
                isA(TestContainerConfiguration.class));
        assertThat(container.getServlets(), equalTo(servlets));
        assertThat(container.getFilters(), equalTo(filters));
        assertTrue(container.isStarted());
    }
}
