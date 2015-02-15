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

package shiver.me.timbers.junit.runner.tomcat.test.one;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.FILTERED;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.INIT;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PACKAGE_FILTER_NAME;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.PARAM;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.URL_PATTERN;

@WebFilter(
        urlPatterns = URL_PATTERN,
        filterName = PACKAGE_FILTER_NAME,
        initParams = @WebInitParam(name = INIT, value = PARAM),
        asyncSupported = true
)
public class PackageTestFilter implements Filter {

    public static final AtomicReference<Boolean> LOADED = new AtomicReference<>();
    public static final AtomicReference<String> NAME = new AtomicReference<>();
    public static final AtomicReference<String> INIT_PARAM_VALUE = new AtomicReference<>();
    public static final AtomicReference<Boolean> ASYNC_SUPPORTED = new AtomicReference<>();

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