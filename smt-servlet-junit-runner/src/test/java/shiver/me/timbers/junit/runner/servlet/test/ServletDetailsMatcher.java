package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;

/**
 * @author Karl Bennett
 */
public class ServletDetailsMatcher extends TypeSafeMatcher<ServletDetail> {

    public static Matcher<ServletDetail> equalTo(ServletDetail expected) {
        return new ServletDetailsMatcher(expected);
    }

    public static WithMatcher<ServletDetail> servletDetailsMatcher() {
        return new WithMatcher<ServletDetail>() {
            @Override
            public Matcher matcher(ServletDetail expected) {
                return equalTo(expected);
            }
        };
    }

    private final ServletDetail expected;

    public ServletDetailsMatcher(ServletDetail expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(ServletDetail actual) {

        if (!expected.getName().equals(actual.getName())) {
            return false;
        }

        if (!expected.getUrlPatterns().equals(actual.getUrlPatterns())) {
            return false;
        }

        if (expected.loadOnStartup() != actual.loadOnStartup()) {
            return false;
        }

        if (!expected.getInitParams().equals(actual.getInitParams())) {
            return false;
        }

        if (expected.asyncSupported() != actual.asyncSupported()) {
            return false;
        }

        if (!expected.getSmallIcon().equals(actual.getSmallIcon())) {
            return false;
        }

        if (!expected.getLargeIcon().equals(actual.getLargeIcon())) {
            return false;
        }

        if (!expected.getDescription().equals(actual.getDescription())) {
            return false;
        }

        if (!expected.getDisplayName().equals(actual.getDisplayName())) {
            return false;
        }

        if (!expected.getServlet().getClass().equals(actual.getServlet().getClass())) {
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
