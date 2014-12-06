package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.FilterDetails;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.ServletDetails;
import shiver.me.timbers.junit.runner.servlet.Servlets;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Constants {

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
