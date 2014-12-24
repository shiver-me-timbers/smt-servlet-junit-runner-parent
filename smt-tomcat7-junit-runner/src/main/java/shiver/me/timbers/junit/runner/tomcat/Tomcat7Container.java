package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import shiver.me.timbers.junit.runner.servlet.*;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.PortConfiguration;
import shiver.me.timbers.junit.runner.tomcat.filter.FilterDetailFilterDef;
import shiver.me.timbers.junit.runner.tomcat.filter.FilterDetailFilterMap;

import javax.servlet.ServletException;

import static java.util.Map.Entry;

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
    public void configure(PortConfiguration portConfiguration) {
        tomcat.setPort(portConfiguration.getPort());
    }

    @Override
    public void configure(ContainerConfiguration<Tomcat> containerConfiguration) {
        containerConfiguration.configure(tomcat);
    }

    @Override
    public void load(Servlets servlets) {

        for (ServletDetail servletDetail : servlets) {

            final String name = servletDetail.getName();

            final Wrapper wrapper = Tomcat.addServlet(context, name, servletDetail.getServlet().getClass().getName());

            wrapper.setLoadOnStartup(servletDetail.loadOnStartup());
            wrapper.setAsyncSupported(servletDetail.asyncSupported());

            for (String urlPattern : servletDetail.getUrlPatterns()) {
                wrapper.addMapping(urlPattern);
            }

            for (Entry<String, String> entry : servletDetail.getInitParams().entrySet()) {
                wrapper.addInitParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public void load(Filters filters) {

        for (FilterDetail filterDetail : filters) {
            context.addFilterDef(new FilterDetailFilterDef(filterDetail));
            context.addFilterMap(new FilterDetailFilterMap(filterDetail));
        }
    }

    @Override
    public void start() {
        withLifeCycle(new LifeCycle() {
            @Override
            public void run() throws LifecycleException {
                tomcat.start();
            }
        });
    }

    @Override
    public void shutdown() {
        withLifeCycle(new LifeCycle() {
            @Override
            public void run() throws LifecycleException {
                tomcat.stop();
            }
        });
    }

    private static void withLifeCycle(LifeCycle run) {
        try {
            run.run();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    private static interface LifeCycle {
        public void run() throws LifecycleException;
    }
}
