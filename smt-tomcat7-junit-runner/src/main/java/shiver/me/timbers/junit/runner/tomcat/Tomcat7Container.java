package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.ServletDetails;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfig;
import shiver.me.timbers.junit.runner.servlet.config.PortConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

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
        for (ServletDetails servletDetails : servlets.getServlets()) {

            final WebServlet webServlet = servletDetails.getWebServlet();

            Tomcat.addServlet(context, webServlet.name(), servletDetails.getServlet());

            context.addServletMapping(findMapping(webServlet), webServlet.name());
        }
    }

    private static String findMapping(WebServlet webServlet) {

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
