package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.lang.annotation.Annotation;

/**
 * @author Karl Bennett
 */
public class ClassWebServlet implements WebServlet {

    private final WebServlet webServlet;

    public ClassWebServlet(Class<? extends Servlet> servlet) {
        this.webServlet = WebServletBuilder.create()
                .withName(servlet.getSimpleName())
                .withValue(new String[]{"/" + servlet.getSimpleName()})
                .build();
    }

    @Override
    public boolean asyncSupported() {
        return webServlet.asyncSupported();
    }

    @Override
    public String description() {
        return webServlet.description();
    }

    @Override
    public String displayName() {
        return webServlet.displayName();
    }

    @Override
    public WebInitParam[] initParams() {
        return webServlet.initParams();
    }

    @Override
    public String largeIcon() {
        return webServlet.largeIcon();
    }

    @Override
    public int loadOnStartup() {
        return webServlet.loadOnStartup();
    }

    @Override
    public String name() {
        return webServlet.name();
    }

    @Override
    public String smallIcon() {
        return webServlet.smallIcon();
    }

    @Override
    public String[] urlPatterns() {
        return webServlet.urlPatterns();
    }

    @Override
    public String[] value() {
        return webServlet.value();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return webServlet.annotationType();
    }
}
