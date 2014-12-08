package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.tomcat.Tomcat7JUnitRunnerServletTest.TestServlet;

@RunWith(Tomcat7JUnitRunner.class)
@ContainerConfiguration(servlets = TestServlet.class)
public class Tomcat7JUnitRunnerServletTest {

    private static final AtomicReference<Boolean> LOADED = new AtomicReference<>();
    private static final AtomicReference<String> SERVLET_NAME = new AtomicReference<>();
    private static final AtomicReference<String> INIT_PARAM_VALUE = new AtomicReference<>();
    private static final AtomicReference<Boolean> ASYNC_SUPPORTED = new AtomicReference<>();

    private static final String INIT = "init";
    private static final String PARAM = "param";
    private static final String NAME = "test-servlet";
    private static final String URL_PATTERN = "/test";
    private static final String SUCCESS = "success";

    @Port
    private int port;

    @Test
    public void Tomcat_can_start_up() {

        // Then
        assertThat(port, greaterThan(0));
        assertTrue(LOADED.get());
        assertEquals(NAME, SERVLET_NAME.get());
        assertEquals(PARAM, INIT_PARAM_VALUE.get());

        final Response response = ClientBuilder.newClient()
                .target(String.format("http://localhost:%d/%s", port, URL_PATTERN))
                .request(TEXT_PLAIN_TYPE).get();


        assertTrue(ASYNC_SUPPORTED.get());
        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals(SUCCESS, response.readEntity(String.class));
    }

    @WebServlet(
            urlPatterns = URL_PATTERN,
            name = NAME,
            asyncSupported = true,
            initParams = @WebInitParam(name = INIT, value = PARAM),
            loadOnStartup = 1
    )
    public static class TestServlet extends HttpServlet {

        @Override
        public void init(ServletConfig config) throws ServletException {
            LOADED.set(true);
            SERVLET_NAME.set(config.getServletName());
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
