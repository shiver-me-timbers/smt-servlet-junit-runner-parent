package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import shiver.me.timbers.junit.runner.servlet.Filters;

import static shiver.me.timbers.junit.runner.servlet.test.ContainsAllMatcher.containsAll;

public class FiltersMatcher extends TypeSafeMatcher<Filters> {

    public static Matcher<Filters> equalTo(Filters expected) {
        return new FiltersMatcher(expected);
    }

    private final Filters expected;

    public FiltersMatcher(Filters expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(Filters actual) {

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
