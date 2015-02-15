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
import shiver.me.timbers.junit.runner.tomcat.test.webxml.WebXmlTestFilter;
import shiver.me.timbers.junit.runner.tomcat.test.webxml.WebXmlTestServlet;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.FILTERED;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.GET;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PARAM;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.SUCCESS;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.URL_PATTERN;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.WEB_XML;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.WEB_XML_FILTER_NAME;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.WEB_XML_SERVLET_NAME;

@ContainerConfiguration(webXml = WEB_XML)
public abstract class ServletJUnitRunnerWebXmlTest {

    @Port
    private int port;

    @Test
    public void Tomcat_can_start_up() {

        // Then
        assertThat(port, greaterThan(0));
        assertTrue(WebXmlTestServlet.LOADED.get());
        assertTrue(WebXmlTestFilter.LOADED.get());
        assertEquals(WEB_XML_SERVLET_NAME, WebXmlTestServlet.NAME.get());
        assertEquals(WEB_XML_FILTER_NAME, WebXmlTestFilter.NAME.get());
        assertEquals(PARAM, WebXmlTestServlet.INIT_PARAM_VALUE.get());
        assertEquals(PARAM, WebXmlTestFilter.INIT_PARAM_VALUE.get());

        final Response response = GET(port, URL_PATTERN);

        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals(FILTERED + SUCCESS, response.readEntity(String.class));

        assertTrue(WebXmlTestServlet.ASYNC_SUPPORTED.get());
        assertTrue(WebXmlTestFilter.ASYNC_SUPPORTED.get());
    }
}
