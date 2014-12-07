package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import shiver.me.timbers.junit.runner.servlet.FilterDetails;

/**
 * @author Karl Bennett
 */
public class FilterDetailsMatcher extends TypeSafeMatcher<FilterDetails> {

    public static Matcher<FilterDetails> equalTo(FilterDetails expected) {
        return new FilterDetailsMatcher(expected);
    }

    public static WithMatcher<FilterDetails> filterDetailsMatcher() {
        return new WithMatcher<FilterDetails>() {
            @Override
            public Matcher matcher(FilterDetails expected) {
                return equalTo(expected);
            }
        };
    }

    private final FilterDetails expected;

    public FilterDetailsMatcher(FilterDetails expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(FilterDetails actual) {

        if (!expected.getDescription().equals(actual.getDescription())) {
            return false;
        }

        if (!expected.getDisplayName().equals(actual.getDisplayName())) {
            return false;
        }

        if (!expected.getInitParams().equals(actual.getInitParams())) {
            return false;
        }

        if (!expected.getFilterName().equals(actual.getFilterName())) {
            return false;
        }

        if (!expected.getSmallIcon().equals(actual.getSmallIcon())) {
            return false;
        }

        if (!expected.getLargeIcon().equals(actual.getLargeIcon())) {
            return false;
        }

        if (!expected.getServletNames().equals(actual.getServletNames())) {
            return false;
        }

        if (!expected.getUrlPatterns().equals(actual.getUrlPatterns())) {
            return false;
        }

        if (!expected.getDispatcherTypes().equals(actual.getDispatcherTypes())) {
            return false;
        }

        if (expected.asyncSupported() != actual.asyncSupported()) {
            return false;
        }

        if (!expected.getFilter().getClass().equals(actual.getFilter().getClass())) {
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
