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
import static org.junit.Assert.*;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.*;
import static shiver.me.timbers.junit.runner.tomcat.test.one.PackageTestServlet.*;

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
        assertEquals(SERVLET_NAME, NAME.get());
        assertEquals(PARAM, INIT_PARAM_VALUE.get());

        final Response response = ClientBuilder.newClient()
                .target(String.format("http://localhost:%d/%s", port, URL_PATTERN))
                .request(TEXT_PLAIN_TYPE).get();


        assertTrue(ASYNC_SUPPORTED.get());
        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals(SUCCESS, response.readEntity(String.class));
    }
}
