package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import static javax.servlet.DispatcherType.REQUEST;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class ClassWebFilterTest {

    @Test
    public void A_filter_class_can_be_used_for_creation() {
        // When
        final ClassWebFilter actual = new ClassWebFilter(Filter.class);

        // Then
        assertThat(actual.description(), isEmptyString());
        assertThat(actual.displayName(), isEmptyString());
        assertArrayEquals(new WebInitParam[0], actual.initParams());
        assertEquals(Filter.class.getSimpleName(), actual.filterName());
        assertThat(actual.smallIcon(), isEmptyString());
        assertThat(actual.largeIcon(), isEmptyString());
        assertArrayEquals(new String[0], actual.servletNames());
        assertArrayEquals(new String[]{"/" + Filter.class.getSimpleName()}, actual.value());
        assertArrayEquals(new String[0], actual.urlPatterns());
        assertArrayEquals(new DispatcherType[]{REQUEST}, actual.dispatcherTypes());
        assertFalse(actual.asyncSupported());
        assertEquals(WebFilter.class, actual.annotationType());
    }
}
