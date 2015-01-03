package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PACKAGE_ONE;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PACKAGE_SERVLET_NAME;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PARAM;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.SUCCESS;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.URL_PATTERN;
import static shiver.me.timbers.junit.runner.tomcat.test.one.PackageTestServlet.ASYNC_SUPPORTED;
import static shiver.me.timbers.junit.runner.tomcat.test.one.PackageTestServlet.INIT_PARAM_VALUE;
import static shiver.me.timbers.junit.runner.tomcat.test.one.PackageTestServlet.LOADED;
import static shiver.me.timbers.junit.runner.tomcat.test.one.PackageTestServlet.NAME;

@RunWith(Tomcat7JUnitRunner.class)
@ContainerConfiguration(packages = PACKAGE_ONE)
public class Tomcat7JUnitRunnerPackageServletTest {

    @Port
    private int port;

    @Test
    public void Tomcat_can_start_up() {

        // Then
        assertThat(port, greaterThan(0));
        assertTrue(LOADED.get());
        assertEquals(PACKAGE_SERVLET_NAME, NAME.get());
        assertEquals(PARAM, INIT_PARAM_VALUE.get());

        final Response response = ClientBuilder.newClient()
                .target(String.format("http://localhost:%d/%s", port, URL_PATTERN))
                .request(TEXT_PLAIN_TYPE).get();


        assertTrue(ASYNC_SUPPORTED.get());
        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals(SUCCESS, response.readEntity(String.class));
    }
}
