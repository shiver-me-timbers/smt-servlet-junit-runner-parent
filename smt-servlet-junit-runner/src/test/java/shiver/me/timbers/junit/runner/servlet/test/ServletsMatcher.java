package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import shiver.me.timbers.junit.runner.servlet.ServletDetails;
import shiver.me.timbers.junit.runner.servlet.Servlets;

import java.util.List;

/**
 * @author Karl Bennett
 */
public class ServletsMatcher extends TypeSafeMatcher<Servlets> {

    public static Matcher<Servlets> equalTo(Servlets expected) {
        return new ServletsMatcher(expected);
    }

    private final Servlets expected;

    public ServletsMatcher(Servlets expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(Servlets actual) {

        final List<ServletDetails> expectedServlets = expected.getServlets();
        final List<ServletDetails> actualServlets = actual.getServlets();

        if (expectedServlets.size() != actualServlets.size()) {
            return false;
        }

        for (int i = 0; i < expectedServlets.size(); i++) {

            final ServletDetails expectedServletDetails = expectedServlets.get(i);
            final ServletDetails actualServletDetails = actualServlets.get(i);

            if (!WebServletMatcher
                    .equalTo(expectedServletDetails.getWebServlet()).matches(actualServletDetails.getWebServlet())) {
                return false;
            }

            if (!expectedServletDetails.getServlet().getClass().equals(actualServletDetails.getServlet().getClass())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
