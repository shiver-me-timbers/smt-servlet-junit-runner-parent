package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.test.BaseFilter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static javax.servlet.DispatcherType.FORWARD;
import static javax.servlet.DispatcherType.REQUEST;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
        assertFilter(actual, AllAnnotatedFilter.class);
    }

    @Test
    public void An_filter_class_annotated_with_no_name_has_one_generated() {

        // When
        final FilterDetail actual = new FilterDetail(AnnotatedWithNoNameFilter.class);

        // Then
        assertEquals(AnnotatedWithNoNameFilter.class.getSimpleName(), actual.getFilterName());
        assertThat(actual.getUrlPatterns(), empty());
        assertDefaults(actual);
        assertFilter(actual, AnnotatedWithNoNameFilter.class);
    }

    @Test
    public void An_filter_class_annotated_with_a_value_produces_the_correct_url_pattern() {

        // When
        final FilterDetail actual = new FilterDetail(AnnotatedWithValueFilter.class);

        // Then
        assertEquals(AnnotatedWithValueFilter.class.getSimpleName(), actual.getFilterName());
        assertEquals(asList(VALUE), actual.getUrlPatterns());
        assertDefaults(actual);
        assertFilter(actual, AnnotatedWithValueFilter.class);
    }

    @Test
    public void An_filter_class_annotated_with_a_url_pattern_produces_the_correct_url_pattern() {

        // When
        final FilterDetail actual = new FilterDetail(AnnotatedWithUrlPatternsFilter.class);

        // Then
        assertEquals(AnnotatedWithUrlPatternsFilter.class.getSimpleName(), actual.getFilterName());
        assertEquals(asList(URL_PATTERN), actual.getUrlPatterns());
        assertDefaults(actual);
        assertFilter(actual, AnnotatedWithUrlPatternsFilter.class);
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
        assertFilter(actual, UnannotatedFilter.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_filter_class_with_a_private_default_constructor_cannot_be_instantiated() {

        // When
        new FilterDetail(PrivateDefaultConstructorFilter.class).getFilterInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_filter_class_with_no_default_constructor_cannot_be_instantiated() {

        // When
        new FilterDetail(NoDefaultConstructorFilter.class).getFilterInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_filter_interface_cannot_be_instantiated() {

        // When
        new FilterDetail(Filter.class).getFilterInstance();
    }

    @Test
    public void A_filter_detail_can_be_checked_for_equality() {

        final Equal<FilterDetail> equal = new Equal<FilterDetail>() {
            @Override
            public boolean equal(FilterDetail left, Object right) {
                return left.equals(right);
            }
        };

        assertEquality(equal);
    }

    @Test
    public void A_filter_detail_can_be_generate_a_hash_code() {

        final Equal<FilterDetail> equal = new Equal<FilterDetail>() {
            @Override
            public boolean equal(FilterDetail left, Object right) {

                if (null == right) {
                    return false;
                }

                return left.hashCode() == right.hashCode();
            }
        };

        assertEquality(equal);
    }

    @Test
    public void A_filter_detail_can_be_to_stringed() {

        final String expected = format(
                "FilterDetail {\n" +
                        "description='%s',\n" +
                        "displayName='%s',\n" +
                        "initParams=%s,\n" +
                        "filterName='%s',\n" +
                        "smallIcon='%s',\n" +
                        "largeIcon='%s',\n" +
                        "servletNames=%s,\n" +
                        "urlPatterns=%s,\n" +
                        "dispatcherTypes=%s,\n" +
                        "asyncSupported=%s,\n" +
                        "filter=%s,\n" +
                        "}",
                DESCRIPTION,
                DISPLAY_NAME,
                INIT_PARAMS,
                FILTER_NAME,
                SMALL_ICON,
                LARGE_ICON,
                asList(SERVLET_NAME),
                asList(URL_PATTERN),
                asList(FORWARD),
                ASYNC_SUPPORT,
                Filter.class
        );

        final String actual = new FilterDetail(new FilterDetailBuilder().withAll().build()).toString();

        assertEquals(expected, actual);
    }

    private static void assertFilter(FilterDetail actual, Class<? extends Filter> filter) {
        assertEquals(filter, actual.getFilter());
        assertThat(actual.getFilterInstance(), instanceOf(filter));
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

    private static void assertEquality(Equal<FilterDetail> equal) {

        final FilterDetail populated = new FilterDetailBuilder().withAll().build();
        final FilterDetail empty = new FilterDetailBuilder().withAsyncSupported(true).build();

        assertEquality(populated, equal, new MockDifferent());
        assertEquality(empty, equal, new MockDifferent());
        assertEquality(populated, equal, new NullDifferent());
    }

    private static void assertEquality(FilterDetail left, Equal<FilterDetail> eq, Different different) {

        assertTrue(eq.equal(left, left));
        assertTrue(eq.equal(left, new FilterDetail(left)));

        assertFalse(eq.equal(left, new FilterDetailBuilder(left).withDescription(different.string()).build()));
        assertFalse(eq.equal(left, new FilterDetailBuilder(left).withDisplayName(different.string()).build()));
        assertFalse(eq.equal(left,
                new FilterDetailBuilder(left).withInitParams(different.map("diff", "erent")).build()));
        assertFalse(eq.equal(left, new FilterDetailBuilder(left).withFilterName(different.string()).build()));
        assertFalse(eq.equal(left, new FilterDetailBuilder(left).withSmallIcon(different.string()).build()));
        assertFalse(eq.equal(left, new FilterDetailBuilder(left).withLargeIcon(different.string()).build()));
        assertFalse(eq.equal(left,
                new FilterDetailBuilder(left).withServletNames(different.list(String.class)).build()));
        assertFalse(eq.equal(left,
                new FilterDetailBuilder(left).withUrlPatterns(different.list(String.class)).build()));
        assertFalse(eq.equal(left,
                new FilterDetailBuilder(left).withDispatcherTypes(different.list(DispatcherType.class)).build()));
        assertFalse(eq.equal(left, new FilterDetailBuilder(left).withAsyncSupported(different.bool()).build()));
        assertFalse(eq.equal(left, new FilterDetailBuilder(left).withFilter(DifferentFilter.class).build()));

        assertFalse(eq.equal(left, new Object()));
        assertFalse(eq.equal(left, null));
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

    @WebFilter
    public static class AnnotatedWithNoNameFilter extends BaseFilter {
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

    public static class DifferentFilter extends BaseFilter {
    }

    private static class FilterDetailBuilder {

        private String description;
        private String displayName;
        private Map<String, String> initParams;
        private String filterName;
        private String smallIcon;
        private String largeIcon;
        private List<String> servletNames;
        private List<String> urlPatterns;
        private List<DispatcherType> dispatcherTypes;
        private boolean asyncSupported;
        private Class<? extends Filter> filter;

        public FilterDetailBuilder() {
        }

        public FilterDetailBuilder(FilterDetail filterDetail) {
            this(
                    filterDetail.getDescription(),
                    filterDetail.getDisplayName(),
                    filterDetail.getInitParams(),
                    filterDetail.getFilterName(),
                    filterDetail.getSmallIcon(),
                    filterDetail.getLargeIcon(),
                    filterDetail.getServletNames(),
                    filterDetail.getUrlPatterns(),
                    filterDetail.getDispatcherTypes(),
                    filterDetail.asyncSupported(),
                    filterDetail.getFilter()
            );
        }

        private FilterDetailBuilder(
                String description,
                String displayName,
                Map<String, String> initParams,
                String filterName,
                String smallIcon,
                String largeIcon,
                List<String> servletNames,
                List<String> urlPatterns,
                List<DispatcherType> dispatcherTypes,
                boolean asyncSupported,
                Class<? extends Filter> filter
        ) {
            this.description = description;
            this.displayName = displayName;
            this.initParams = initParams;
            this.filterName = filterName;
            this.smallIcon = smallIcon;
            this.largeIcon = largeIcon;
            this.servletNames = servletNames;
            this.urlPatterns = urlPatterns;
            this.dispatcherTypes = dispatcherTypes;
            this.asyncSupported = asyncSupported;
            this.filter = filter;
        }

        public FilterDetail build() {
            return new FilterDetail(
                    description,
                    displayName,
                    initParams,
                    filterName,
                    smallIcon,
                    largeIcon,
                    servletNames,
                    urlPatterns,
                    dispatcherTypes,
                    asyncSupported,
                    filter
            );
        }

        public FilterDetailBuilder withAll() {
            withDescription(DESCRIPTION);
            withDisplayName(DISPLAY_NAME);
            withInitParams(INIT_PARAMS);
            withFilterName(FILTER_NAME);
            withSmallIcon(SMALL_ICON);
            withLargeIcon(LARGE_ICON);
            withServletNames(asList(SERVLET_NAME));
            withUrlPatterns(asList(URL_PATTERN));
            withDispatcherTypes(asList(FORWARD));
            withAsyncSupported(ASYNC_SUPPORT);
            withFilter(Filter.class);
            return this;
        }

        public FilterDetailBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public FilterDetailBuilder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public FilterDetailBuilder withInitParams(Map<String, String> initParams) {
            this.initParams = initParams;
            return this;
        }

        public FilterDetailBuilder withFilterName(String filterName) {
            this.filterName = filterName;
            return this;
        }

        public FilterDetailBuilder withSmallIcon(String smallIcon) {
            this.smallIcon = smallIcon;
            return this;
        }

        public FilterDetailBuilder withLargeIcon(String largeIcon) {
            this.largeIcon = largeIcon;
            return this;
        }

        public FilterDetailBuilder withServletNames(List<String> servletNames) {
            this.servletNames = servletNames;
            return this;
        }

        public FilterDetailBuilder withUrlPatterns(List<String> urlPatterns) {
            this.urlPatterns = urlPatterns;
            return this;
        }

        public FilterDetailBuilder withDispatcherTypes(List<DispatcherType> dispatcherTypes) {
            this.dispatcherTypes = dispatcherTypes;
            return this;
        }

        public FilterDetailBuilder withAsyncSupported(boolean asyncSupported) {
            this.asyncSupported = asyncSupported;
            return this;
        }

        public FilterDetailBuilder withFilter(Class<? extends Filter> filter) {
            this.filter = filter;
            return this;
        }
    }
}
