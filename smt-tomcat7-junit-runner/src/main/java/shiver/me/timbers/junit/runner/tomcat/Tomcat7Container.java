package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.ServletDetails;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.config.PortConfig;

import javax.servlet.ServletException;

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
    public void config(ContainerConfiguration<Tomcat> containerConfiguration) {
        containerConfiguration.configure(tomcat);
    }

    @Override
    public void load(Servlets servlets) {

        for (ServletDetails servletDetails : servlets.getServlets()) {

            final String name = servletDetails.getName();

            Tomcat.addServlet(context, name, servletDetails.getServlet());

            for (String urlPattern : servletDetails.getUrlPatterns()) {
                context.addServletMapping(urlPattern, name);
            }
        }
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
