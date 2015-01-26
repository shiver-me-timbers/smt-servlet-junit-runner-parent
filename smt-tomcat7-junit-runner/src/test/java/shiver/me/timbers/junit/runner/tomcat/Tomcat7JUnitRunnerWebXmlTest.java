package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith(Tomcat7JUnitRunner.class)
@ContainerConfiguration(webXml = WEB_XML)
public class Tomcat7JUnitRunnerWebXmlTest {

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
