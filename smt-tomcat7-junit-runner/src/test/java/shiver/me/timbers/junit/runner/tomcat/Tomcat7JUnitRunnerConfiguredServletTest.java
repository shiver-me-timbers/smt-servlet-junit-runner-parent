package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.annotation.ServletConfiguration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.tomcat.Tomcat7JUnitRunnerConfiguredServletTest.TestServlet;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.GET;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.INIT;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PARAM;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.SERVLET_NAME;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.SUCCESS;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.URL_PATTERN;

@RunWith(Tomcat7JUnitRunner.class)
@ContainerConfiguration(
        servletConfigurations = @ServletConfiguration(
                configuration = @WebServlet(
                        urlPatterns = URL_PATTERN,
                        name = SERVLET_NAME,
                        asyncSupported = true,
                        initParams = @WebInitParam(name = INIT, value = PARAM),
                        loadOnStartup = 1
                ),
                servlet = TestServlet.class
        )
)
public class Tomcat7JUnitRunnerConfiguredServletTest {

    private static final AtomicReference<Boolean> LOADED = new AtomicReference<>();
    private static final AtomicReference<String> NAME = new AtomicReference<>();
    private static final AtomicReference<String> INIT_PARAM_VALUE = new AtomicReference<>();
    private static final AtomicReference<Boolean> ASYNC_SUPPORTED = new AtomicReference<>();

    @Port
    private int port;

    @Test
    public void The_servlet_is_loaded_correctly() {

        // Then
        assertThat(port, greaterThan(0));
        assertTrue(LOADED.get());
        assertEquals(SERVLET_NAME, NAME.get());
        assertEquals(PARAM, INIT_PARAM_VALUE.get());

        final Response response = GET(port, URL_PATTERN);

        assertTrue(ASYNC_SUPPORTED.get());
        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals(SUCCESS, response.readEntity(String.class));
    }


    public static class TestServlet extends HttpServlet {

        @Override
        public void init(ServletConfig config) throws ServletException {
            LOADED.set(true);
            NAME.set(config.getServletName());
            INIT_PARAM_VALUE.set(config.getInitParameter(INIT));
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            ASYNC_SUPPORTED.set(request.isAsyncSupported());

            response.setStatus(OK.getStatusCode());
            response.setContentType(TEXT_PLAIN);

            final PrintWriter writer = response.getWriter();
            writer.write(SUCCESS);
            writer.flush();
        }
    }
}