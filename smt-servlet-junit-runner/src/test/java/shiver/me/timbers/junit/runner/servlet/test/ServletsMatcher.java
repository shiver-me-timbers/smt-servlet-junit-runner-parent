package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;

import java.util.List;

import static shiver.me.timbers.junit.runner.servlet.test.ContainsAllMatcher.containsAll;
import static shiver.me.timbers.junit.runner.servlet.test.ServletDetailsMatcher.servletDetailsMatcher;

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

        final List<ServletDetail> expectedServlets = expected.getServlets();
        final List<ServletDetail> actualServlets = actual.getServlets();

        if (expectedServlets.size() != actualServlets.size()) {
            return false;
        }

        if (!containsAll(expectedServlets).with(servletDetailsMatcher()).matches(actualServlets)) {
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
