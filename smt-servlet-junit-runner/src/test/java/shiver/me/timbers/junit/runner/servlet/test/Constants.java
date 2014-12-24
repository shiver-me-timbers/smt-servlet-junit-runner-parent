package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageFilterOne;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageServletOne;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageFilterTwo;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageFilterThree;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageServletThree;

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

    public static Servlets mockPackageServlets() {

        return mockServlets(new ArrayList<ServletDetail>() {{
            add(new ServletDetail(PackageServletOne.class));
            add(new ServletDetail(PackageServletTwo.class));
            add(new ServletDetail(PackageServletThree.class));
        }});
    }

    public static Servlets mockAllServlets() {

        final ArrayList<ServletDetail> servlets = new ArrayList<>();
        servlets.addAll(mockServlets().asList());
        servlets.addAll(mockPackageServlets().asList());

        return mockServlets(servlets);
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

    public static Filters mockPackageFilters() {

        return mockFilters(new ArrayList<FilterDetail>() {{
            add(new FilterDetail(PackageFilterOne.class));
            add(new FilterDetail(PackageFilterTwo.class));
            add(new FilterDetail(PackageFilterThree.class));
        }});
    }

    public static Filters mockAllFilters() {

        final ArrayList<FilterDetail> filters = new ArrayList<>();
        filters.addAll(mockFilters().asList());
        filters.addAll(mockPackageFilters().asList());

        return mockFilters(filters);
    }

    public static Filters mockFilters(ArrayList<FilterDetail> list) {

        final Filters mock = mock(Filters.class);
        when(mock.asList()).thenReturn(list);
        when(mock.iterator()).thenReturn(list.iterator());

        return mock;
    }
}
