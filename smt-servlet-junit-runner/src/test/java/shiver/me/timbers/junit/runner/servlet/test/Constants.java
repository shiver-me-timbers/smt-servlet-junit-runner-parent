package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.Packages;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Constants {

    public static final String INIT = "init";
    public static final String PARAM = "param";

    public static final String DESCRIPTION = "description";
    public static final String DISPLAY_NAME = "displayName";
    public static final Map<String, String> INIT_PARAMS = singletonMap(INIT, PARAM);
    public static final String SMALL_ICON = "smallIcon";
    public static final String LARGE_ICON = "largeIcon";
    public static final String VALUE = "/value";
    public static final String URL_PATTERN = "/url-pattern";
    public static final boolean ASYNC_SUPPORT = true;

    public static final String PACKAGE_ONE = "shiver.me.timbers.junit.runner.servlet.test.one";
    public static final String PACKAGE_TWO = "shiver.me.timbers.junit.runner.servlet.test.two";
    public static final String PACKAGE_THREE = "shiver.me.timbers.junit.runner.servlet.test.three";

    public static Servlets mockEmptyServlets() {

        return mockServlets(new ArrayList<ServletDetail>());
    }

    public static Servlets mockServlets() {

        return mockServlets(new ArrayList<ServletDetail>() {{
            add(new ServletDetail(ServletOne.class));
            add(new ServletDetail(ServletTwo.class));
            add(new ServletDetail(ServletThree.class));
        }});
    }

    public static Servlets mockServlets(ArrayList<ServletDetail> list) {

        final Servlets mock = mock(Servlets.class);
        when(mock.asList()).thenReturn(list);
        when(mock.iterator()).thenReturn(list.iterator());

        return mock;
    }

    public static Filters mockEmptyFilters() {

        return mockFilters(new ArrayList<FilterDetail>());
    }

    public static Filters mockFilters() {

        return mockFilters(new ArrayList<FilterDetail>() {{
            add(new FilterDetail(FilterOne.class));
            add(new FilterDetail(FilterTwo.class));
            add(new FilterDetail(FilterThree.class));
        }});
    }

    public static Filters mockFilters(ArrayList<FilterDetail> list) {

        final Filters mock = mock(Filters.class);
        when(mock.asList()).thenReturn(list);
        when(mock.iterator()).thenReturn(list.iterator());

        return mock;
    }

    public static Packages mockEmptyPackages() {

        return mockPackages(new ArrayList<URL>());
    }

    public static Packages mockPackages() {

        return mockPackages(new ArrayList<URL>() {{
            add(toURL(PACKAGE_ONE));
            add(toURL(PACKAGE_TWO));
            add(toURL(PACKAGE_THREE));
        }});
    }

    private static URL toURL(String pkg) {
        return Thread.currentThread().getContextClassLoader().getResource(pkg.replaceAll("\\.", "/"));
    }

    public static Packages mockPackages(ArrayList<URL> list) {

        final Packages mock = mock(Packages.class);
        when(mock.asList()).thenReturn(list);
        when(mock.iterator()).thenReturn(list.iterator());

        return mock;
    }
}
