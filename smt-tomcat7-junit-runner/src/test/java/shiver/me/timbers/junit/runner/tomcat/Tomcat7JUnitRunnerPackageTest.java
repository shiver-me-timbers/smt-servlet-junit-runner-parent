package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith(Tomcat7JUnitRunner.class)
@ContainerConfiguration(packages = PACKAGE_ONE)
public class Tomcat7JUnitRunnerPackageTest {

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
