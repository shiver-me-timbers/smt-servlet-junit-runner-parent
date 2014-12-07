package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.test.BaseFilter;

import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.util.HashMap;

import static java.util.Arrays.asList;
import static javax.servlet.DispatcherType.FORWARD;
import static javax.servlet.DispatcherType.REQUEST;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.ASYNC_SUPPORT;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.DESCRIPTION;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.DISPLAY_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.INIT_PARAMS;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.LARGE_ICON;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.SMALL_ICON;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.URL_PATTERN;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.VALUE;

public class FilterDetailTest {

    private static final String INIT = "init";
    private static final String PARAM = "param";

    private static final String FILTER_NAME = "filter-name";
    private static final String SERVLET_NAME = "servlet-name";

    @Test
    public void An_annotated_filter_class_can_be_used_for_creation() {

        // When
        final FilterDetail actual = new FilterDetail(AllAnnotatedFilter.class);

        // Then
        assertEquals(DESCRIPTION, actual.getDescription());
        assertEquals(DISPLAY_NAME, actual.getDisplayName());
        assertEquals(INIT_PARAMS, actual.getInitParams());
        assertEquals(FILTER_NAME, actual.getFilterName());
        assertEquals(SMALL_ICON, actual.getSmallIcon());
        assertEquals(LARGE_ICON, actual.getLargeIcon());
        assertEquals(asList(SERVLET_NAME), actual.getServletNames());
        assertEquals(asList(VALUE), actual.getUrlPatterns());
        assertEquals(asList(FORWARD), actual.getDispatcherTypes());
        assertEquals(ASYNC_SUPPORT, actual.asyncSupported());
        assertThat(actual.getFilter(), instanceOf(AllAnnotatedFilter.class));
    }

    @Test
    public void An_filter_class_annotated_with_a_value_produces_the_correct_url_pattern() {

        // When
        final FilterDetail actual = new FilterDetail(AnnotatedWithValueFilter.class);

        // Then
        assertThat(actual.getFilterName(), isEmptyString());
        assertEquals(asList(VALUE), actual.getUrlPatterns());
        assertDefaults(actual);
        assertThat(actual.getFilter(), instanceOf(AnnotatedWithValueFilter.class));
    }

    @Test
    public void An_filter_class_annotated_with_a_url_pattern_produces_the_correct_url_pattern() {

        // When
        final FilterDetail actual = new FilterDetail(AnnotatedWithUrlPatternsFilter.class);

        // Then
        assertThat(actual.getFilterName(), isEmptyString());
        assertEquals(asList(URL_PATTERN), actual.getUrlPatterns());
        assertDefaults(actual);
        assertThat(actual.getFilter(), instanceOf(AnnotatedWithUrlPatternsFilter.class));
    }

    @Test
    public void An_unannotated_filter_class_can_be_used_for_creation() {

        // Given
        final String name = UnannotatedFilter.class.getSimpleName();
        final String urlPattern = "/" + name;

        // When
        final FilterDetail actual = new FilterDetail(UnannotatedFilter.class);

        // Then
        assertEquals(name, actual.getFilterName());
        assertEquals(asList(urlPattern), actual.getUrlPatterns());
        assertDefaults(actual);
        assertThat(actual.getFilter(), instanceOf(UnannotatedFilter.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_filter_class_with_a_private_default_constructor_cannot_be_used_for_creation() {

        // When
        new FilterDetail(PrivateDefaultConstructorFilter.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_filter_class_with_no_default_constructor_cannot_be_used_for_creation() {

        // When
        new FilterDetail(NoDefaultConstructorFilter.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_filter_interface_cannot_be_used_for_creation() {

        // When
        new FilterDetail(Filter.class);
    }

    @WebFilter(
            description = DESCRIPTION,
            displayName = DISPLAY_NAME,
            initParams = @WebInitParam(name = INIT, value = PARAM),
            filterName = FILTER_NAME,
            smallIcon = SMALL_ICON,
            largeIcon = LARGE_ICON,
            servletNames = SERVLET_NAME,
            value = VALUE,
            urlPatterns = URL_PATTERN,
            dispatcherTypes = FORWARD,
            asyncSupported = ASYNC_SUPPORT

    )
    public static class AllAnnotatedFilter extends BaseFilter {
    }

    private static void assertDefaults(FilterDetail actual) {
        assertThat(actual.getDescription(), isEmptyString());
        assertThat(actual.getDisplayName(), isEmptyString());
        assertEquals(new HashMap<String, String>(), actual.getInitParams());
        assertThat(actual.getSmallIcon(), isEmptyString());
        assertThat(actual.getLargeIcon(), isEmptyString());
        assertThat(actual.getServletNames(), empty());
        assertEquals(asList(REQUEST), actual.getDispatcherTypes());
        assertFalse(actual.asyncSupported());
    }

    @WebFilter(VALUE)
    public static class AnnotatedWithValueFilter extends BaseFilter {
    }

    @WebFilter(urlPatterns = URL_PATTERN)
    public static class AnnotatedWithUrlPatternsFilter extends BaseFilter {
    }

    public static class UnannotatedFilter extends BaseFilter {
    }

    public static class PrivateDefaultConstructorFilter extends BaseFilter {
        private PrivateDefaultConstructorFilter() {
        }
    }

    public static class NoDefaultConstructorFilter extends BaseFilter {
        public NoDefaultConstructorFilter(Object arg) {
        }
    }
}
