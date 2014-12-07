package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;

import java.util.List;

import static shiver.me.timbers.junit.runner.servlet.test.ContainsAllMatcher.containsAll;
import static shiver.me.timbers.junit.runner.servlet.test.FilterDetailsMatcher.filterDetailsMatcher;

/**
 * @author Karl Bennett
 */
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

        final List<FilterDetail> expectedFilters = expected.getFilters();
        final List<FilterDetail> actualFilters = actual.getFilters();

        if (expectedFilters.size() != actualFilters.size()) {
            return false;
        }

        if (!containsAll(expectedFilters).with(filterDetailsMatcher()).matches(actualFilters)) {
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
