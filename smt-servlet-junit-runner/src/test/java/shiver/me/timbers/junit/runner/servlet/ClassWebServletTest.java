package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * @author Karl Bennett
 */
public class ClassWebServletTest {

    @Test
    public void A_servlet_class_can_be_used_for_creation() {
        // When
        final ClassWebServlet actual = new ClassWebServlet(Servlet.class);

        // Then
        assertEquals(Servlet.class.getSimpleName(), actual.name());
        assertArrayEquals(new String[]{"/" + Servlet.class.getSimpleName()}, actual.value());
        assertArrayEquals(new String[0], actual.urlPatterns());
        assertEquals(-1, actual.loadOnStartup());
        assertArrayEquals(new WebInitParam[0], actual.initParams());
        assertFalse(actual.asyncSupported());
        assertThat(actual.smallIcon(), isEmptyString());
        assertThat(actual.largeIcon(), isEmptyString());
        assertThat(actual.description(), isEmptyString());
        assertThat(actual.displayName(), isEmptyString());
        assertEquals(WebServlet.class, actual.annotationType());
    }
}
