package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.JarScanner;
import org.apache.tomcat.JarScannerCallback;
import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;
import shiver.me.timbers.junit.runner.tomcat.filter.FilterDetailFilterDef;
import shiver.me.timbers.junit.runner.tomcat.filter.FilterDetailFilterMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.net.URL;
import java.util.Set;

import static java.util.Map.Entry;

/**
 * @author Karl Bennett
 */
public class Tomcat7Container implements Container<Tomcat> {

    private final Tomcat tomcat;
    private final Context context;

    public Tomcat7Container(Tomcat tomcat) throws ServletException {
        this.tomcat = tomcat;
        // Give the Tomcat engine instance a unique name so that it's global MBeans don't clash with other Tomcats in
        // this JVM.
        setUniqueEngineName(this.tomcat);
        context = this.tomcat.addWebapp(this.tomcat.getHost(), "/", "/");
        // Disable the Jar scanning so that only the classes that are configured in the test are loaded and the Tomcat
        // startup time is drastically decreased.
        context.setJarScanner(new JarScanner() {
            @Override
            public void scan(ServletContext ct, ClassLoader cl, JarScannerCallback cb, Set<String> jts) {
            }
        });
    }

    private static void setUniqueEngineName(Tomcat tomcat) {
        final Engine engine = tomcat.getEngine();
        engine.setName(engine.getName() + System.identityHashCode(tomcat));
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

            final Wrapper wrapper = Tomcat.addServlet(context, name, servletDetail.getServlet().getName());

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
    public void load(URL webXml) {
        context.setAltDDName(webXml.getPath());
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
