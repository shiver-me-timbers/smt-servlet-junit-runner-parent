package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.JarScanner;
import org.apache.tomcat.JarScannerCallback;
import org.slf4j.bridge.SLF4JBridgeHandler;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Set;
import java.util.concurrent.Callable;

import static java.util.Map.Entry;

/**
 * @author Karl Bennett
 */
public class Tomcat7Container implements Container<Tomcat> {

    private final Tomcat tomcat;
    private final Context context;
    private int identityHash;

    static {
        // Override the normal Tomcat Juli logging with slf4j.
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    public Tomcat7Container(Tomcat tomcat) throws ServletException {
        this.tomcat = tomcat;

        identityHash = System.identityHashCode(tomcat);

        // Give the Tomcat engine instance a unique name so that it's global MBeans don't clash with other Tomcats in
        // this JVM.
        setUniqueEngineName(this.tomcat, identityHash);
        context = this.tomcat.addWebapp(this.tomcat.getHost(), "/", "/");
        // Disable the Jar scanning so that only the classes that are configured in the test are loaded and the Tomcat
        // startup time is drastically decreased.
        context.setJarScanner(new JarScanner() {
            @Override
            public void scan(ServletContext ct, ClassLoader cl, JarScannerCallback cb, Set<String> jts) {
            }
        });
    }

    private static void setUniqueEngineName(Tomcat tomcat, int instanchHash) {
        final Engine engine = tomcat.getEngine();
        engine.setName(engine.getName() + instanchHash);
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

        if (isInJar(webXml)) {
            context.setAltDDName(copy(webXml, tempWebXml(identityHash)));
            return;
        }

        context.setAltDDName(webXml.getPath());
    }

    private static boolean isInJar(URL webXml) {
        return webXml.getPath().contains(".jar!");
    }

    static String copy(final URL input, final File output) {

        return withException(new Callable<String>() {
            @Override
            public String call() throws IOException {
                final InputStream inputStream = input.openStream();
                final FileOutputStream outputStream = new FileOutputStream(output);

                copyAndClose(inputStream, outputStream);

                return output.getAbsolutePath();
            }
        });
    }

    static void copyAndClose(final InputStream input, final OutputStream output) {

        withException(new Callable<Void>() {
            @Override
            public Void call() throws IOException {
                try {
                    IOUtils.copy(input, output);
                    return null;
                } finally {
                    input.close();
                    output.close();
                }
            }
        });
    }

    static File tempWebXml(final int identityHash) {
        return withException(new Callable<File>() {
            @Override
            public File call() throws IOException {
                return new File(Files.createTempDirectory("tomcat-" + identityHash).toFile(), "web.xml");
            }
        });
    }

    @Override
    public void start() {
        withException(new Callable<Void>() {
            @Override
            public Void call() throws LifecycleException {
                tomcat.start();
                return null;
            }
        });
    }

    @Override
    public void shutdown() {
        withException(new Callable<Void>() {
            @Override
            public Void call() throws LifecycleException {
                tomcat.stop();
                return null;
            }
        });
    }

    static <T> T withException(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
