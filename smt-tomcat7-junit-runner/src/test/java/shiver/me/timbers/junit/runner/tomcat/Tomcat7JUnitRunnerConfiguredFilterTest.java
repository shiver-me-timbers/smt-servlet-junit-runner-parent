package shiver.me.timbers.junit.runner.tomcat;

import org.junit.Test;
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.FilterConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
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
import static shiver.me.timbers.junit.runner.tomcat.Tomcat7JUnitRunnerConfiguredFilterTest.TestFilter;
import static shiver.me.timbers.junit.runner.tomcat.Tomcat7JUnitRunnerConfiguredFilterTest.TestServlet;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.FILTER_NAME;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.GET;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.INIT;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PARAM;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.URL_PATTERN;

@RunWith(Tomcat7JUnitRunner.class)
@ContainerConfiguration(
        filterConfigurations = @FilterConfiguration(
                configuration = @WebFilter(
                        urlPatterns = URL_PATTERN,
                        filterName = FILTER_NAME,
                        initParams = @WebInitParam(name = INIT, value = PARAM),
                        asyncSupported = true
                ),
                servlet = TestFilter.class
        ),
        servlets = TestServlet.class
)
public class Tomcat7JUnitRunnerConfiguredFilterTest {

    private static final AtomicReference<Boolean> LOADED = new AtomicReference<>();
    private static final AtomicReference<String> NAME = new AtomicReference<>();
    private static final AtomicReference<String> INIT_PARAM_VALUE = new AtomicReference<>();
    private static final AtomicReference<Boolean> ASYNC_SUPPORTED = new AtomicReference<>();

    private static final String FILTERED = "filtered";

    @Port
    private int port;

    @Test
    public void Tomcat_can_start_up() {

        // Then
        assertThat(port, greaterThan(0));
        assertTrue(LOADED.get());
        assertEquals(FILTER_NAME, NAME.get());
        assertEquals(PARAM, INIT_PARAM_VALUE.get());

        final Response response = GET(port, URL_PATTERN);

        assertTrue(ASYNC_SUPPORTED.get());
        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals(FILTERED, response.readEntity(String.class));
    }

    public static class TestFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            LOADED.set(true);
            NAME.set(filterConfig.getFilterName());
            INIT_PARAM_VALUE.set(filterConfig.getInitParameter(INIT));
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {

            ASYNC_SUPPORTED.set(request.isAsyncSupported());

            response.setContentType(TEXT_PLAIN);

            final PrintWriter writer = response.getWriter();
            writer.write(FILTERED);
            writer.flush();

            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
        }
    }

    @WebServlet(URL_PATTERN)
    public static class TestServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        }
    }
}
