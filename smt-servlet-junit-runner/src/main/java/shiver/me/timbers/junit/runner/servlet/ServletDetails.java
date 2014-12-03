package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

/**
 * This class contains the instantiated servlet and all the details that can be retrieved about it.
 *
 * @author Karl Bennett
 */
public class ServletDetails {

    private static WebServlet webServlet(Class<? extends Servlet> servlet) {

        final WebServlet webServlet = servlet.getAnnotation(WebServlet.class);

        if (null == webServlet) {
            return new ClassWebServlet(servlet);
        }

        return webServlet;
    }

    private static Servlet instantiate(Class<? extends Servlet> servlet) {
        try {
            return servlet.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private final WebServlet webServlet;
    private final Servlet servlet;

    public ServletDetails(Class<? extends Servlet> servlet) {
        this(webServlet(servlet), instantiate(servlet));
    }

    public ServletDetails(WebServlet webServlet, Servlet servlet) {
        this.webServlet = webServlet;
        this.servlet = servlet;
    }

    public WebServlet getWebServlet() {
        return webServlet;
    }

    public Servlet getServlet() {
        return servlet;
    }
}
