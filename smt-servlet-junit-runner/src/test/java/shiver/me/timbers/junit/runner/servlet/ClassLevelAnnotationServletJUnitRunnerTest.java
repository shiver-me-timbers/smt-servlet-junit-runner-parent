/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.FilterConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.annotation.ServletConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.TestAnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.servlet.test.TestContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.TestServletContainer;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PORT;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.WEB_XML_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.WEB_XML_URL;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.CONFIGURED_FILTER_DETAIL_ONE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.CONFIGURED_FILTER_DETAIL_ONE_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.CONFIGURED_FILTER_DETAIL_THREE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.CONFIGURED_FILTER_DETAIL_THREE_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.mockAllFilters;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_ONE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_THREE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_TWO;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.CONFIGURED_SERVLET_DETAIL_ONE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.CONFIGURED_SERVLET_DETAIL_ONE_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.CONFIGURED_SERVLET_DETAIL_TWO_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.CONFIGURED_SERVLET_DETAIL_TWO_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.mockAllServlets;
import static shiver.me.timbers.junit.runner.servlet.test.TestContainerConfiguration.TEST_SERVLET_CONTAINER_REFERENCE;

@RunWith(TestAnnotationServletJUnitRunner.class)
@ContainerConfiguration(
        port = PORT,
        value = TestContainerConfiguration.class,
        servlets = {ServletOne.class, ServletTwo.class, ServletThree.class},
        servletConfigurations = {
                @ServletConfiguration(
                        configuration = @WebServlet(
                                name = CONFIGURED_SERVLET_DETAIL_ONE_NAME,
                                value = CONFIGURED_SERVLET_DETAIL_ONE_PATH
                        ),
                        servlet = ServletOne.class
                ),
                @ServletConfiguration(
                        configuration = @WebServlet(
                                name = CONFIGURED_SERVLET_DETAIL_TWO_NAME,
                                value = CONFIGURED_SERVLET_DETAIL_TWO_PATH
                        ),
                        servlet = ServletTwo.class
                )
        },
        filters = {FilterOne.class, FilterTwo.class, FilterThree.class},
        filterConfigurations = {
                @FilterConfiguration(
                        configuration = @WebFilter(
                                filterName = CONFIGURED_FILTER_DETAIL_ONE_NAME,
                                value = CONFIGURED_FILTER_DETAIL_ONE_PATH
                        ),
                        servlet = FilterOne.class
                ),
                @FilterConfiguration(
                        configuration = @WebFilter(
                                filterName = CONFIGURED_FILTER_DETAIL_THREE_NAME,
                                value = CONFIGURED_FILTER_DETAIL_THREE_PATH
                        ),
                        servlet = FilterTwo.class
                )
        },
        packages = {PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE},
        webXml = WEB_XML_PATH
)
public class ClassLevelAnnotationServletJUnitRunnerTest {

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
        assertThat(container.getServlets(), equalAll(servlets));
        assertThat(container.getFilters(), equalAll(filters));
        assertEquals(WEB_XML_URL, container.getWebXml());
        assertTrue(container.isStarted());
    }
}
