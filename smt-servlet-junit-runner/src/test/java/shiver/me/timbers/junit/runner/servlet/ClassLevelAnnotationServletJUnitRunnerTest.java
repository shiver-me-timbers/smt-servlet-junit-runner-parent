package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;
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
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PACKAGE_ONE;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PACKAGE_THREE;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PACKAGE_TWO;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockFilters;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockPackages;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockServlets;
import static shiver.me.timbers.junit.runner.servlet.test.FiltersMatcher.equalTo;
import static shiver.me.timbers.junit.runner.servlet.test.PackagessMatcher.equalTo;
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
        final Servlets servlets = mockServlets();
        final Filters filters = mockFilters();
        final Packages packages = mockPackages();

        // Then
        assertEquals(PORT, port);
        assertEquals(PORT, container.getPort());
        assertThat((TestContainerConfiguration) container.getContainerConfiguration(),
                isA(TestContainerConfiguration.class));
        assertThat(container.getServlets(), equalTo(servlets));
        assertThat(container.getFilters(), equalTo(filters));
        assertThat(container.getPackages(), equalTo(packages));
        assertTrue(container.isStarted());
    }
}
