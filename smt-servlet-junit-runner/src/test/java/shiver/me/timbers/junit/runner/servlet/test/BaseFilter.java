package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Karl Bennett
 */
public class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    }

    @Override
    public void destroy() {
    }
}
