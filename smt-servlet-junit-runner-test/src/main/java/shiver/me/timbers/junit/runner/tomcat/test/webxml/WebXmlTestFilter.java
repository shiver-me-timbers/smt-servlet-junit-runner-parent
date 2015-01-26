package shiver.me.timbers.junit.runner.tomcat.test.webxml;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.FILTERED;
import static shiver.me.timbers.junit.runner.tomcat.test.Constants.INIT;

public class WebXmlTestFilter implements Filter {

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