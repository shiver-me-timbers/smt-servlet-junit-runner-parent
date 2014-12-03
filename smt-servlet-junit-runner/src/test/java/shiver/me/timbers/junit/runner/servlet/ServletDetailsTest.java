package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.WebServletMatcher.equalTo;

public class ServletDetailsTest {

    private static final String VALID_SERVLET_NAME = "valid-servlet";
    private static final String VALID_SERVLET_VALUE = "/valid";

    @Test
    public void An_annotated_servlet_class_can_be_used_for_creation() {

        // Given
        final WebServlet webServlet = WebServletBuilder.create()
                .withName(VALID_SERVLET_NAME).withValue(new String[]{VALID_SERVLET_VALUE}).build();

        // When
        final ServletDetails actual = new ServletDetails(AnnotatedServlet.class);

        // Then
        assertThat(actual.getWebServlet(), equalTo(webServlet));
        assertThat(actual.getServlet(), instanceOf(AnnotatedServlet.class));
    }

    @Test
    public void An_unannotated_servlet_class_can_be_used_for_creation() {

        // Given
        final String name = UnannotatedServlet.class.getSimpleName();
        final WebServlet webServlet = WebServletBuilder.create()
                .withName(name).withValue(new String[]{"/" + name}).build();

        // When
        final ServletDetails actual = new ServletDetails(UnannotatedServlet.class);

        // Then
        assertThat(actual.getWebServlet(), equalTo(webServlet));
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

    @WebServlet(name = VALID_SERVLET_NAME, value = VALID_SERVLET_VALUE)
    public static class AnnotatedServlet extends HttpServlet {
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
