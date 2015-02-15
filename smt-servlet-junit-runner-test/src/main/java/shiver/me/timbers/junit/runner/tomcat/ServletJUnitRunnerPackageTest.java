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

package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.tomcat.test.one.PackageTestFilter;
import shiver.me.timbers.junit.runner.tomcat.test.one.PackageTestServlet;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.FILTERED;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.GET;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PACKAGE_FILTER_NAME;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PACKAGE_ONE;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PACKAGE_SERVLET_NAME;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PARAM;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.SUCCESS;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.URL_PATTERN;

@ContainerConfiguration(packages = PACKAGE_ONE)
public abstract class ServletJUnitRunnerPackageTest {

    @Port
    private int port;

    @Test
    public void Tomcat_can_start_up() {

        // Then
        assertThat(port, greaterThan(0));
        assertTrue(PackageTestServlet.LOADED.get());
        assertTrue(PackageTestFilter.LOADED.get());
        assertEquals(PACKAGE_SERVLET_NAME, PackageTestServlet.NAME.get());
        assertEquals(PACKAGE_FILTER_NAME, PackageTestFilter.NAME.get());
        assertEquals(PARAM, PackageTestServlet.INIT_PARAM_VALUE.get());
        assertEquals(PARAM, PackageTestFilter.INIT_PARAM_VALUE.get());

        final Response response = GET(port, URL_PATTERN);

        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals(FILTERED + SUCCESS, response.readEntity(String.class));

        assertTrue(PackageTestServlet.ASYNC_SUPPORTED.get());
        assertTrue(PackageTestFilter.ASYNC_SUPPORTED.get());
    }
}
