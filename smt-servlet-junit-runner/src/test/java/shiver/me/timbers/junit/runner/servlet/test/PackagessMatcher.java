package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import shiver.me.timbers.junit.runner.servlet.Packages;

import static shiver.me.timbers.junit.runner.servlet.test.ContainsAllMatcher.containsAll;

/**
 * @author Karl Bennett
 */
public class PackagessMatcher extends TypeSafeMatcher<Packages> {

    public static Matcher<Packages> equalTo(Packages expected) {
        return new PackagessMatcher(expected);
    }

    private final Packages expected;

    public PackagessMatcher(Packages expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(Packages actual) {

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
