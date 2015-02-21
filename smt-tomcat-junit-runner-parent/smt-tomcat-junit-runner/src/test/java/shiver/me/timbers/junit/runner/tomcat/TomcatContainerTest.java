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

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Callable;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static javax.servlet.DispatcherType.REQUEST;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.tomcat.TomcatContainer.copy;
import static shiver.me.timbers.junit.runner.tomcat.TomcatContainer.copyAndClose;
import static shiver.me.timbers.junit.runner.tomcat.TomcatContainer.tempWebXml;
import static shiver.me.timbers.junit.runner.tomcat.TomcatContainer.withException;

public class TomcatContainerTest {

    public static final String WEB_XML = "web.xml";
    public static final URL WEB_XML_JAR = Thread.currentThread().getContextClassLoader().getResource(WEB_XML);

    private TomcatWrapper<Object, Object, Object, FilterDefWrapper, FilterMapWrapper> tomcat;
    private Object jarScanner;
    private EngineWrapper engine;
    private Object host;
    private ContextWrapper<Object, FilterDefWrapper, FilterMapWrapper> context;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {

        tomcat = mock(TomcatWrapper.class);
        jarScanner = new Object();
        engine = mock(EngineWrapper.class);
        host = new Object();
        context = mock(ContextWrapper.class);

        when(tomcat.getWrappedEngine()).thenReturn(engine);
        when(tomcat.getHost()).thenReturn(host);
        when(tomcat.addWebApp(host, "/", "/")).thenReturn(context);
    }

    @Test
    public void Tomcat_is_initialised_correctly() {

        final String name = "engine name";

        // Given
        when(engine.getName()).thenReturn(name);

        // When
        new TomcatContainer<>(tomcat, jarScanner);

        // Then
        verify(tomcat).getWrappedEngine();
        verify(tomcat).getHost();
        verify(tomcat).addWebApp(host, "/", "/");
        verify(engine).getName();
        verify(engine).setName(matches(name + "\\d+"));
        verify(context).setJarScanner(jarScanner);
        verifyNoMoreInteractions(tomcat, engine, context);
    }

    @Test
    public void Can_configure_the_servers_port() {

        final PortConfiguration portConfiguration = mock(PortConfiguration.class);
        final int port = 8080;

        // Given
        when(portConfiguration.getPort()).thenReturn(port);

        // When
        new TomcatContainer<>(tomcat, jarScanner).configure(portConfiguration);

        // Then
        verify(portConfiguration).getPort();
        verify(tomcat).setPort(port);
        verifyNoMoreInteractions(portConfiguration);
    }

    @Test
    public void Can_configure_the_server() {

        @SuppressWarnings("unchecked")
        final ContainerConfiguration<Object> containerConfiguration = mock(ContainerConfiguration.class);
        final Object expected = new Object();

        // Given
        when(tomcat.getDelegate()).thenReturn(expected);

        // When
        new TomcatContainer<>(tomcat, jarScanner).configure(containerConfiguration);

        // Then
        verify(containerConfiguration).configure(expected);
        verifyNoMoreInteractions(containerConfiguration);
    }

    @Test
    public void Can_load_servlets() {

        final Servlets servlets = mock(Servlets.class);
        final ServletDetail servletDetail = mock(ServletDetail.class);
        final ServletWrapper servletWrapper = mock(ServletWrapper.class);

        final String name = "servlet name";
        final Servlet servlet = mock(Servlet.class);
        final int loadOnStartup = 1;
        final boolean asyncSupported = true;
        final String urlPattern = "url pattern";
        final String initParamKey = "init param key";
        final String initParamValue = "init param value";

        // Given
        when(tomcat.addServlet(name, servlet)).thenReturn(servletWrapper);

        when(servlets.iterator()).thenReturn(singletonList(servletDetail).iterator());

        when(servletDetail.getName()).thenReturn(name);
        when(servletDetail.getServletInstance()).thenReturn(servlet);
        when(servletDetail.loadOnStartup()).thenReturn(loadOnStartup);
        when(servletDetail.asyncSupported()).thenReturn(asyncSupported);
        when(servletDetail.getUrlPatterns()).thenReturn(singletonList(urlPattern));
        when(servletDetail.getInitParams()).thenReturn(singletonMap(initParamKey, initParamValue));

        // When
        new TomcatContainer<>(tomcat, jarScanner).load(servlets);

        // Then
        verify(servletWrapper).setLoadOnStartup(loadOnStartup);
        verify(servletWrapper).setAsyncSupported(asyncSupported);
        verify(servletWrapper).addMapping(urlPattern);
        verify(servletWrapper).addInitParameter(initParamKey, initParamValue);
        verifyNoMoreInteractions(servletWrapper);
    }

    @Test
    public void Can_load_filters() {

        final Filters filters = mock(Filters.class);
        final FilterDetail filterDetail = mock(FilterDetail.class);
        final FilterDefWrapper filterDef = mock(FilterDefWrapper.class);
        final FilterMapWrapper filterMap = mock(FilterMapWrapper.class);

        final Filter filter = mock(Filter.class);
        final String description = "filter description";
        final String displayName = "display Name";
        final String filterName = "filter name";
        final String smallIcon = "small icon";
        final String largeIcon = "large icon";
        final String asyncSupported = "true";
        final String initParamKey = "init param key";
        final String initParamValue = "init param value";
        final String servletName = "servlet name";
        final String urlPattern = "url pattern";
        final DispatcherType dispatcherType = REQUEST;

        // Given
        when(filters.iterator()).thenReturn(singletonList(filterDetail).iterator());

        when(context.createFilterDef()).thenReturn(filterDef);
        when(context.createFilterMap()).thenReturn(filterMap);

        when(filterDetail.getFilterInstance()).thenReturn(filter);
        when(filterDetail.getDescription()).thenReturn(description);
        when(filterDetail.getDisplayName()).thenReturn(displayName);
        when(filterDetail.getFilterName()).thenReturn(filterName);
        when(filterDetail.getSmallIcon()).thenReturn(smallIcon);
        when(filterDetail.getLargeIcon()).thenReturn(largeIcon);
        when(filterDetail.asyncSupported()).thenReturn(Boolean.valueOf(asyncSupported));
        when(filterDetail.getInitParams()).thenReturn(singletonMap(initParamKey, initParamValue));

        when(filterDetail.getServletNames()).thenReturn(singletonList(servletName));
        when(filterDetail.getUrlPatterns()).thenReturn(singletonList(urlPattern));
        when(filterDetail.getDispatcherTypes()).thenReturn(singletonList(dispatcherType));

        // When
        new TomcatContainer<>(tomcat, jarScanner).load(filters);

        // Then
        verify(filterDef).setFilter(filter);
        verify(filterDef).setDescription(description);
        verify(filterDef).setDisplayName(displayName);
        verify(filterDef).setFilterName(filterName);
        verify(filterDef).setSmallIcon(smallIcon);
        verify(filterDef).setLargeIcon(largeIcon);
        verify(filterDef).setAsyncSupported(asyncSupported);
        verify(filterDef).addInitParameter(initParamKey, initParamValue);

        verify(filterMap).setFilterName(filterName);
        verify(filterMap).addServletName(servletName);
        verify(filterMap).addURLPattern(urlPattern);
        verify(filterMap).setDispatcher(dispatcherType.name());

        verify(context).addFilterDef(filterDef);
        verify(context).addFilterMap(filterMap);
    }

    @Test
    public void Can_load_web_xml_from_jar() {

        // When
        new TomcatContainer<>(tomcat, jarScanner).load(WEB_XML_JAR);

        // Then
        verify(context).setAltDDName(contains(WEB_XML));
        verify(context).setAltDDName(argThat(not(containsString(".jar!"))));
    }

    @Test
    public void Can_load_web_xml_from_filesystem() throws MalformedURLException {

        // Given
        final URL webXml = new URL("file:/" + WEB_XML);

        // When
        new TomcatContainer<>(tomcat, jarScanner).load(webXml);

        // Then
        verify(context).setAltDDName("/" + WEB_XML);
    }

    @Test
    public void Can_start_server() throws Exception {

        // When
        new TomcatContainer<>(tomcat, jarScanner).start();

        // Then
        verify(tomcat).start();
    }

    @Test
    public void Can_stop_server() throws Exception {

        // When
        new TomcatContainer<>(tomcat, jarScanner).shutdown();

        // Then
        verify(tomcat).stop();
    }

    @Test
    public void It_is_possible_to_run_with_a_life_cycle() {

        // Given
        final Callable<Boolean> lifeCycle = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                return true;
            }
        };

        // When
        final Boolean actual = withException(lifeCycle);

        // Then
        assertTrue(actual);
    }

    @Test(expected = RuntimeException.class)
    public void It_is_possible_to_run_with_a_life_cycle_and_have_it_fail() {

        // Given
        final Callable<Void> lifeCycle = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                throw new Exception();
            }
        };

        // When
        withException(lifeCycle);
    }

    @Test
    public void It_is_possible_to_create_a_temporary_web_xml() throws IOException {

        // Given
        final int hash = new Random().nextInt();

        // When
        final File webXml = tempWebXml(hash);

        // Then
        assertTrue(webXml.createNewFile());
    }

    @Test
    public void It_is_possible_to_copy_and_close_a_jar_web_xml_to_the_filesystem() throws IOException {

        // Given
        final int hash = new Random().nextInt();
        final File webXml = tempWebXml(hash);
        final InputStream input = WEB_XML_JAR.openStream();
        final OutputStream output = new FileOutputStream(webXml);

        // When
        copyAndClose(input, output);

        // Then
        assertTrue(webXml.exists());
    }

    @Test(expected = RuntimeException.class)
    public void It_is_not_possible_to_copy_and_close_from_an_invalid_input() throws IOException {

        final InputStream input = mock(InputStream.class);
        final OutputStream output = mock(OutputStream.class);

        // Given
        when(input.read(any(byte[].class))).thenThrow(new IOException());

        // When
        copyAndClose(input, output);
    }

    @Test(expected = RuntimeException.class)
    public void It_is_not_possible_to_copy_and_close_from_an_invalid_output() throws IOException {

        final InputStream input = WEB_XML_JAR.openStream();
        final OutputStream output = mock(OutputStream.class);

        // Given
        doThrow(new IOException()).when(output).write(any(byte[].class), any(Integer.class), any(Integer.class));

        // When
        copyAndClose(input, output);
    }

    @Test
    public void It_is_possible_to_copy_a_jar_web_xml_to_the_filesystem() {

        // Given
        final int hash = new Random().nextInt();
        final File output = tempWebXml(hash);

        // When
        final String webXml = copy(WEB_XML_JAR, output);

        // Then
        assertTrue(new File(webXml).exists());
    }

    @Test(expected = RuntimeException.class)
    public void It_is_not_possible_to_copy_from_an_invalid_web_xml() throws MalformedURLException {

        // Given
        final int hash = new Random().nextInt();
        final URL input = new URL("file:/invalid.jar!web.xml");
        final File output = tempWebXml(hash);

        // When
        copy(input, output);
    }

    @Test(expected = RuntimeException.class)
    public void It_is_not_possible_to_copy_to_an_invalid_web_xml() {

        // Given
        final File output = new File("invalid/web.xml");

        // When
        copy(WEB_XML_JAR, output);
    }
}
