package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import javax.servlet.annotation.WebServlet;
import java.util.Arrays;

/**
 * @author Karl Bennett
 */
public class WebServletMatcher extends TypeSafeMatcher<WebServlet> {

    public static Matcher<WebServlet> equalTo(WebServlet expected) {
        return new WebServletMatcher(expected);
    }

    private final WebServlet expected;

    public WebServletMatcher(WebServlet expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(WebServlet actual) {

        if (!expected.name().equals(actual.name())) {
            return false;
        }

        if (!Arrays.equals(expected.value(), actual.value())) {
            return false;
        }

        if (!Arrays.equals(expected.urlPatterns(), actual.urlPatterns())) {
            return false;
        }

        if (expected.loadOnStartup() != actual.loadOnStartup()) {
            return false;
        }

        if (!Arrays.equals(expected.initParams(), actual.initParams())) {
            return false;
        }

        if (expected.asyncSupported() != actual.asyncSupported()) {
            return false;
        }

        if (!expected.smallIcon().equals(actual.smallIcon())) {
            return false;
        }

        if (!expected.largeIcon().equals(actual.largeIcon())) {
            return false;
        }

        if (!expected.description().equals(actual.description())) {
            return false;
        }

        if (!expected.displayName().equals(actual.displayName())) {
            return false;
        }

        if (!expected.annotationType().equals(actual.annotationType())) {
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
