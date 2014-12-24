package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import static javax.servlet.DispatcherType.FORWARD;
import static javax.servlet.DispatcherType.REQUEST;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class WebFilterBuilderTest {

    @Test
    public void A_web_filter_can_be_built() {
        // Given
        final String description = "description";
        final String displayName = "displayName";
        final WebInitParam initParams = mock(WebInitParam.class);
        final String filterName = "filterName";
        final String smallIcon = "smallIcon";
        final String largeIcon = "largeIcon";
        final String servletNames = "servletNames";
        final String value = "value";
        final String urlPatterns = "urlPatterns";
        final DispatcherType dispatcherTypes = FORWARD;
        final boolean asyncSupported = true;

        // When
        final WebFilter actual = WebFilterBuilder.create()
                .withDescription(description)
                .withDisplayName(displayName)
                .withInitParams(initParams)
                .withFilterName(filterName)
                .withSmallIcon(smallIcon)
                .withLargeIcon(largeIcon)
                .withValue(value)
                .withServletNames(servletNames)
                .withUrlPatterns(urlPatterns)
                .withDispatcherTypes(dispatcherTypes)
                .withAsyncSupported(asyncSupported)
                .build();

        // Then
        assertEquals(description, actual.description());
        assertEquals(displayName, actual.displayName());
        assertArrayEquals(new WebInitParam[]{initParams}, actual.initParams());
        assertEquals(filterName, actual.filterName());
        assertEquals(smallIcon, actual.smallIcon());
        assertEquals(largeIcon, actual.largeIcon());
        assertArrayEquals(new String[]{servletNames}, actual.servletNames());
        assertArrayEquals(new String[]{value}, actual.value());
        assertArrayEquals(new String[]{urlPatterns}, actual.urlPatterns());
        assertArrayEquals(new DispatcherType[]{dispatcherTypes}, actual.dispatcherTypes());
        assertEquals(asyncSupported, actual.asyncSupported());
        assertEquals(WebFilter.class, actual.annotationType());
    }

    @Test
    public void A_default_web_filter_can_be_built() {

        // When
        final WebFilter actual = WebFilterBuilder.create().build();

        // Then
        assertThat(actual.description(), isEmptyString());
        assertThat(actual.displayName(), isEmptyString());
        assertArrayEquals(new WebInitParam[0], actual.initParams());
        assertThat(actual.filterName(), isEmptyString());
        assertThat(actual.smallIcon(), isEmptyString());
        assertThat(actual.largeIcon(), isEmptyString());
        assertArrayEquals(new String[0], actual.servletNames());
        assertArrayEquals(new String[0], actual.value());
        assertArrayEquals(new String[0], actual.urlPatterns());
        assertArrayEquals(new DispatcherType[]{REQUEST}, actual.dispatcherTypes());
        assertFalse(actual.asyncSupported());
        assertEquals(WebFilter.class, actual.annotationType());
    }
}
