package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfig;
import shiver.me.timbers.junit.runner.servlet.config.PortConfig;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class Tomcat7Container implements Container<Tomcat> {

    private final Tomcat tomcat;
    private final Context context;

    public Tomcat7Container(Tomcat tomcat) throws ServletException {
        this.tomcat = tomcat;
        context = this.tomcat.addWebapp(this.tomcat.getHost(), "/", "/");
    }

    @Override
    public void config(PortConfig portConfig) {
        tomcat.setPort(portConfig.getPort());
    }

    @Override
    public void config(ContainerConfig<Tomcat> containerConfig) {
        containerConfig.configure(tomcat);
    }

    @Override
    public void load(Servlets servlets) {
        for (Class<? extends Servlet> servlet : servlets.getServlets()) {
            final String name = findName(servlet);
            Tomcat.addServlet(context, name, instantiate(servlet));
            context.addServletMapping(findMapping(servlet), name);
        }
    }

    private static String findName(Class<? extends Servlet> servlet) {

        final WebServlet webServlet = servlet.getAnnotation(WebServlet.class);

        if (null == webServlet) {
            return servlet.getSimpleName();
        }

        return webServlet.name();
    }

    private static Servlet instantiate(Class<? extends Servlet> servlet) {
        try {
            return servlet.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(
                    format("The servlet (%s) must have a public default constructor.", servlet.getName()),
                    e
            );
        }
    }

    private static String findMapping(Class<? extends Servlet> servlet) {

        final WebServlet webServlet = servlet.getAnnotation(WebServlet.class);

        if (null != webServlet) {

            final String[] value = webServlet.value();

            if (0 < value.length) {
                return value[0];
            }

            final String[] urlPatterns = webServlet.urlPatterns();

            if (0 < urlPatterns.length) {
                return urlPatterns[0];
            }
        }

        return "/*";
    }

    @Override
    public void start() {
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdown() {
        try {
            tomcat.stop();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}
