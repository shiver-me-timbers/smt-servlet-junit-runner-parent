package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ServletDetailsTest {

    private static final String INIT = "init";
    private static final String PARAM = "param";

    private static final String VALID_NAME = "valid-servlet";
    private static final String VALID_VALUE = "/valid";
    private static final String VALID_URL_PATTERN = "/url-pattern";
    private static final boolean VALID_LOAD_ON_STARTUP = true;
    private static final Map<String, String> VALID_INIT_PARAMS = singletonMap(INIT, PARAM);
    private static final boolean VALID_ASYNC_SUPPORT = true;
    private static final String VALID_SMALL_ICON = "smallIcon";
    private static final String VALID_LARGE_ICON = "largeIcon";
    private static final String VALID_DESCRIPTION = "description";
    private static final String VALID_DISPLAY_NAME = "displayName";

    @Test
    public void An_annotated_servlet_class_can_be_used_for_creation() {

        // When
        final ServletDetails actual = new ServletDetails(AllAnnotatedServlet.class);

        // Then
        assertEquals(VALID_NAME, actual.getName());
        assertEquals(asList(VALID_VALUE), actual.getUrlPatterns());
        assertEquals(VALID_LOAD_ON_STARTUP, actual.loadOnStartup());
        assertEquals(VALID_INIT_PARAMS, actual.getInitParams());
        assertEquals(VALID_ASYNC_SUPPORT, actual.asyncSupported());
        assertEquals(VALID_SMALL_ICON, actual.getSmallIcon());
        assertEquals(VALID_LARGE_ICON, actual.getLargeIcon());
        assertEquals(VALID_DESCRIPTION, actual.getDescription());
        assertEquals(VALID_DISPLAY_NAME, actual.getDisplayName());
        assertThat(actual.getServlet(), instanceOf(AllAnnotatedServlet.class));
    }

    @Test
    public void An_servlet_class_annotated_with_a_value_produces_the_correct_url_pattern() {

        // When
        final ServletDetails actual = new ServletDetails(AnnotatedWithValueServlet.class);

        // Then
        assertThat(actual.getName(), isEmptyString());
        assertEquals(asList(VALID_VALUE), actual.getUrlPatterns());
        assertDefaults(actual);
        assertThat(actual.getServlet(), instanceOf(AnnotatedWithValueServlet.class));
    }

    @Test
    public void An_servlet_class_annotated_with_a_url_pattern_produces_the_correct_url_pattern() {

        // When
        final ServletDetails actual = new ServletDetails(AnnotatedWithUrlPatternsServlet.class);

        // Then
        assertThat(actual.getName(), isEmptyString());
        assertEquals(asList(VALID_URL_PATTERN), actual.getUrlPatterns());
        assertDefaults(actual);
        assertThat(actual.getServlet(), instanceOf(AnnotatedWithUrlPatternsServlet.class));
    }

    @Test
    public void An_unannotated_servlet_class_can_be_used_for_creation() {

        // Given
        final String name = UnannotatedServlet.class.getSimpleName();
        final String urlPattern = "/" + name;

        // When
        final ServletDetails actual = new ServletDetails(UnannotatedServlet.class);

        // Then
        assertEquals(name, actual.getName());
        assertEquals(asList(urlPattern), actual.getUrlPatterns());
        assertDefaults(actual);
        assertThat(actual.getServlet(), instanceOf(UnannotatedServlet.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_servlet_class_with_a_private_default_constructor_cannot_be_used_for_creation() {

        // When
        new ServletDetails(PrivateDefaultConstructorServlet.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_servlet_class_with_no_default_constructor_cannot_be_used_for_creation() {

        // When
        new ServletDetails(NoDefaultConstructorServlet.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_servlet_interface_cannot_be_used_for_creation() {

        // When
        new ServletDetails(Servlet.class);
    }

    private static void assertDefaults(ServletDetails actual) {
        assertFalse(actual.loadOnStartup());
        assertEquals(new HashMap<String, String>(), actual.getInitParams());
        assertFalse(actual.asyncSupported());
        assertThat(actual.getSmallIcon(), isEmptyString());
        assertThat(actual.getLargeIcon(), isEmptyString());
        assertThat(actual.getDescription(), isEmptyString());
        assertThat(actual.getDisplayName(), isEmptyString());
    }

    @WebServlet(
            name = VALID_NAME,
            value = VALID_VALUE,
            urlPatterns = VALID_URL_PATTERN,
            loadOnStartup = 1,
            initParams = @WebInitParam(name = INIT, value = PARAM),
            asyncSupported = VALID_ASYNC_SUPPORT,
            smallIcon = VALID_SMALL_ICON,
            largeIcon = VALID_LARGE_ICON,
            description = VALID_DESCRIPTION,
            displayName = VALID_DISPLAY_NAME
    )
    public static class AllAnnotatedServlet extends HttpServlet {
    }

    @WebServlet(VALID_VALUE)
    public static class AnnotatedWithValueServlet extends HttpServlet {
    }

    @WebServlet(urlPatterns = VALID_URL_PATTERN)
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
