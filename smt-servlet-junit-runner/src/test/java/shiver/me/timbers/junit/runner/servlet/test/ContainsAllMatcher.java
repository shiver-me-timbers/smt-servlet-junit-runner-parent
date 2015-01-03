package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * This matcher can be used to check that two {@link Iterable}s contain the same elements. The order of the elements is
 * ignored.
 *
 * @author Karl Bennett
 */
public class ContainsAllMatcher<I extends Iterable> extends TypeSafeMatcher<I> {

    private final I expected;

    public ContainsAllMatcher(I expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(I actual) {

        for (Object exp : expected) {
            if (!matchFound(actual, exp)) {
                return false;
            }
        }

        return true;
    }

    private boolean matchFound(I actual, Object exp) {

        for (Object act : actual) {
            if (exp.equals(act)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
