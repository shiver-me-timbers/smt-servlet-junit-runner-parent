package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.FilterDetails;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.ServletDetails;
import shiver.me.timbers.junit.runner.servlet.Servlets;

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

    public static Servlets mockServlets() {

        final Servlets mock = mock(Servlets.class);
        when(mock.getServlets()).thenReturn(new ArrayList<ServletDetails>() {{
            add(new ServletDetails(ServletOne.class));
            add(new ServletDetails(ServletTwo.class));
            add(new ServletDetails(ServletThree.class));
        }});

        return mock;
    }

    public static Filters mockFilters() {

        final Filters mock = mock(Filters.class);
        when(mock.getFilters()).thenReturn(new ArrayList<FilterDetails>() {{
            add(new FilterDetails(FilterOne.class));
            add(new FilterDetails(FilterTwo.class));
            add(new FilterDetails(FilterThree.class));
        }});

        return mock;
    }
}
