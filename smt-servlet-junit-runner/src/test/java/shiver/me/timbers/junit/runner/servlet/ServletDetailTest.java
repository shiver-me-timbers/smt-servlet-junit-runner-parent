package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.ASYNC_SUPPORT;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.DESCRIPTION;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.DISPLAY_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.INIT;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.INIT_PARAMS;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.LARGE_ICON;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PARAM;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.SMALL_ICON;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.URL_PATTERN;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.VALUE;

public class ServletDetailTest {

    private static final String NAME = "valid-servlet";
    private static final int LOAD_ON_STARTUP = 1;

    @Test
    public void An_annotated_servlet_class_can_be_used_for_creation() {

        // When
        final ServletDetail actual = new ServletDetail(AllAnnotatedServlet.class);

        // Then
        assertEquals(NAME, actual.getName());
        assertEquals(asList(VALUE), actual.getUrlPatterns());
        assertEquals(LOAD_ON_STARTUP, actual.loadOnStartup());
        assertEquals(INIT_PARAMS, actual.getInitParams());
        assertEquals(ASYNC_SUPPORT, actual.asyncSupported());
        assertEquals(SMALL_ICON, actual.getSmallIcon());
        assertEquals(LARGE_ICON, actual.getLargeIcon());
        assertEquals(DESCRIPTION, actual.getDescription());
        assertEquals(DISPLAY_NAME, actual.getDisplayName());
        assertServlet(actual, AllAnnotatedServlet.class);
    }

    @Test
    public void An_servlet_class_annotated_with_a_value_produces_the_correct_url_pattern() {

        // When
        final ServletDetail actual = new ServletDetail(AnnotatedWithValueServlet.class);

        // Then
        assertThat(actual.getName(), isEmptyString());
        assertEquals(asList(VALUE), actual.getUrlPatterns());
        assertDefaults(actual);
        assertServlet(actual, AnnotatedWithValueServlet.class);
    }

    @Test
    public void An_servlet_class_annotated_with_a_url_pattern_produces_the_correct_url_pattern() {

        // When
        final ServletDetail actual = new ServletDetail(AnnotatedWithUrlPatternsServlet.class);

        // Then
        assertThat(actual.getName(), isEmptyString());
        assertEquals(asList(URL_PATTERN), actual.getUrlPatterns());
        assertDefaults(actual);
        assertServlet(actual, AnnotatedWithUrlPatternsServlet.class);
    }

    @Test
    public void An_unannotated_servlet_class_can_be_used_for_creation() {

        // Given
        final String name = UnannotatedServlet.class.getSimpleName();
        final String urlPattern = "/" + name;

        // When
        final ServletDetail actual = new ServletDetail(UnannotatedServlet.class);

        // Then
        assertEquals(name, actual.getName());
        assertEquals(asList(urlPattern), actual.getUrlPatterns());
        assertDefaults(actual);
        assertServlet(actual, UnannotatedServlet.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_servlet_class_with_a_private_default_constructor_cannot_be_instantiated() {

        // When
        new ServletDetail(PrivateDefaultConstructorServlet.class).getServletInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_servlet_class_with_no_default_constructor_cannot_be_instantiated() {

        // When
        new ServletDetail(NoDefaultConstructorServlet.class).getServletInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_servlet_interface_cannot_be_instantiated() {

        // When
        new ServletDetail(Servlet.class).getServletInstance();
    }

    private static void assertServlet(ServletDetail actual, Class<? extends Servlet> servlet) {
        assertEquals(servlet, actual.getServlet());
        assertThat(actual.getServletInstance(), instanceOf(servlet));
    }

    private static void assertDefaults(ServletDetail actual) {
        assertEquals(-1, actual.loadOnStartup());
        assertEquals(new HashMap<String, String>(), actual.getInitParams());
        assertFalse(actual.asyncSupported());
        assertThat(actual.getSmallIcon(), isEmptyString());
        assertThat(actual.getLargeIcon(), isEmptyString());
        assertThat(actual.getDescription(), isEmptyString());
        assertThat(actual.getDisplayName(), isEmptyString());
    }

    @WebServlet(
            name = NAME,
            value = VALUE,
            urlPatterns = URL_PATTERN,
            loadOnStartup = LOAD_ON_STARTUP,
            initParams = @WebInitParam(name = INIT, value = PARAM),
            asyncSupported = ASYNC_SUPPORT,
            smallIcon = SMALL_ICON,
            largeIcon = LARGE_ICON,
            description = DESCRIPTION,
            displayName = DISPLAY_NAME
    )
    public static class AllAnnotatedServlet extends HttpServlet {
    }

    @WebServlet(VALUE)
    public static class AnnotatedWithValueServlet extends HttpServlet {
    }

    @WebServlet(urlPatterns = URL_PATTERN)
    public static class AnnotatedWithUrlPatternsServlet extends HttpServlet {
    }

    public static class UnannotatedServlet extends HttpServlet {
    }

    public static class PrivateDefaultConstructorServlet extends HttpServlet {
        private PrivateDefaultConstructorServlet() {
        }
    }

    public static class NoDefaultConstructorServlet extends HttpServlet {
        public NoDefaultConstructorServlet(Object arg) {
        }
    }
}
