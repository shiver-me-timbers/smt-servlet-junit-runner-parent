package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import shiver.me.timbers.junit.runner.servlet.Servlets;

import static shiver.me.timbers.junit.runner.servlet.test.ContainsAllMatcher.containsAll;

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

        if (expected.asList().size() != actual.asList().size()) {
            return false;
        }

        if (!containsAll(expected).matchesSafely(actual)) {
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
