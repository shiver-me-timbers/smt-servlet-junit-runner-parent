/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.tomcat;

import org.apache.commons.io.IOUtils;
import org.slf4j.bridge.SLF4JBridgeHandler;
import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;

import javax.servlet.DispatcherType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.concurrent.Callable;

import static java.lang.String.format;
import static java.util.Map.Entry;

/**
 * @author Karl Bennett
 */
public class TomcatContainer<D, FD extends FilterDefWrapper, FM extends FilterMapWrapper> implements Container<D> {

    private final TomcatWrapper<D, FD, FM> tomcat;
    private final ContextWrapper<FD, FM> context;

    private final int identityHash;

    static {
        // Override the normal Tomcat Juli logging with slf4j.
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    public TomcatContainer(TomcatWrapper<D, FD, FM> tomcat, JarScannerWrapper jarScanner) {
        this.tomcat = tomcat;

        identityHash = System.identityHashCode(tomcat);

        // Give the Tomcat engine instance a unique name so that it's global MBeans don't clash with other Tomcats in
        // this JVM.
        setUniqueEngineName(this.tomcat, identityHash);

        context = this.tomcat.addWebapp(this.tomcat.getHost(), "/", "/");
        // Disable the Jar scanning so that only the classes that are configured in the test are loaded and the Tomcat
        // startup time is drastically decreased.
        context.setJarScanner(jarScanner);
    }

    private static void setUniqueEngineName(TomcatWrapper wrapper, int identityHash) {
        final EngineWrapper engine = wrapper.getEngine();
        engine.setName(format("%s%d", engine.getName(), identityHash));
    }

    @Override
    public void configure(PortConfiguration portConfiguration) {
        tomcat.setPort(portConfiguration.getPort());
    }

    @Override
    public void configure(ContainerConfiguration<D> containerConfiguration) {
        containerConfiguration.configure(tomcat.getDelegate());
    }

    @Override
    public void load(Servlets servlets) {

        for (ServletDetail servletDetail : servlets) {

            final String name = servletDetail.getName();

            final ServletWrapper wrapper = tomcat.addServlet(name, servletDetail.getServletInstance());

            applyDetails(wrapper, servletDetail);
        }
    }

    private static void applyDetails(ServletWrapper wrapper, ServletDetail servletDetail) {

        wrapper.setLoadOnStartup(servletDetail.loadOnStartup());
        wrapper.setAsyncSupported(servletDetail.asyncSupported());

        for (String urlPattern : servletDetail.getUrlPatterns()) {
            wrapper.addMapping(urlPattern);
        }

        for (Entry<String, String> entry : servletDetail.getInitParams().entrySet()) {
            wrapper.addInitParameter(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void load(Filters filters) {

        for (FilterDetail filterDetail : filters) {
            context.addFilterDef(populateFilterDef(filterDetail));
            context.addFilterMap(populateFilterMap(filterDetail));
        }
    }

    private FD populateFilterDef(FilterDetail filterDetail) {

        final FD filterDef = context.createFilterDef();

        filterDef.setFilter(filterDetail.getFilterInstance());
        filterDef.setDescription(filterDetail.getDescription());
        filterDef.setDisplayName(filterDetail.getDisplayName());
        filterDef.setFilterName(filterDetail.getFilterName());
        filterDef.setSmallIcon(filterDetail.getSmallIcon());
        filterDef.setLargeIcon(filterDetail.getLargeIcon());
        filterDef.setAsyncSupported(String.valueOf(filterDetail.asyncSupported()));

        for (Entry<String, String> initParam : filterDetail.getInitParams().entrySet()) {
            filterDef.addInitParameter(initParam.getKey(), initParam.getValue());
        }

        return filterDef;
    }

    private FM populateFilterMap(FilterDetail filterDetail) {

        final FM filterMap = context.createFilterMap();

        filterMap.setFilterName(filterDetail.getFilterName());

        for (String servletName : filterDetail.getServletNames()) {
            filterMap.addServletName(servletName);
        }

        for (String urlPattern : filterDetail.getUrlPatterns()) {
            filterMap.addURLPattern(urlPattern);
        }

        // Each call to FilterMap.setDispatcher(String) with a different DispatcherType actually sets a bit flag for
        // each type.
        for (DispatcherType dispatcherType : filterDetail.getDispatcherTypes()) {
            filterMap.setDispatcher(dispatcherType.name());
        }

        return filterMap;
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
            public Void call() throws Exception {
                tomcat.start();
                return null;
            }
        });
    }

    @Override
    public void shutdown() {
        withException(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
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
