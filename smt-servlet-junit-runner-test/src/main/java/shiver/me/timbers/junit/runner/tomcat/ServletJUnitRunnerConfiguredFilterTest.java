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
import static shiver.me.timbers.junit.runner.tomcat.ServletJUnitRunnerConfiguredFilterTest.TestFilter;
import static shiver.me.timbers.junit.runner.tomcat.ServletJUnitRunnerConfiguredFilterTest.TestServlet;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.FILTERED;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.FILTER_NAME;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.GET;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.INIT;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PARAM;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.URL_PATTERN;

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
public abstract class ServletJUnitRunnerConfiguredFilterTest {

    private static final AtomicReference<Boolean> LOADED = new AtomicReference<>();
    private static final AtomicReference<String> NAME = new AtomicReference<>();
    private static final AtomicReference<String> INIT_PARAM_VALUE = new AtomicReference<>();
    private static final AtomicReference<Boolean> ASYNC_SUPPORTED = new AtomicReference<>();

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

        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals(FILTERED, response.readEntity(String.class));

        assertTrue(ASYNC_SUPPORTED.get());
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
