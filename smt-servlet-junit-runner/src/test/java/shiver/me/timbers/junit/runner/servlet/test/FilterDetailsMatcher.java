package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;

/**
 * @author Karl Bennett
 */
public class FilterDetailsMatcher extends TypeSafeMatcher<FilterDetail> {

    public static Matcher<FilterDetail> equalTo(FilterDetail expected) {
        return new FilterDetailsMatcher(expected);
    }

    public static WithMatcher<FilterDetail> filterDetailsMatcher() {
        return new WithMatcher<FilterDetail>() {
            @Override
            public Matcher matcher(FilterDetail expected) {
                return equalTo(expected);
            }
        };
    }

    private final FilterDetail expected;

    public FilterDetailsMatcher(FilterDetail expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(FilterDetail actual) {

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
