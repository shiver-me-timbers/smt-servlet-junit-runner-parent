package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * This matcher can be used to check that two {@link Iterable}s contain the same elements. The order of the elements is
 * ignored.
 *
 * @author Karl Bennett
 */
public class ContainsAllMatcher<E, I extends Iterable<E>> extends TypeSafeMatcher<I> {

    public static <E, I extends Iterable<E>> ContainsAllMatcher<E, I> containsAll(I expected) {
        return new ContainsAllMatcher<>(expected);
    }

    private final I expected;

    public ContainsAllMatcher(I expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(I actual) {

        for (E exp : expected) {
            if (!matchFound(actual, exp)) {
                return false;
            }
        }

        return true;
    }

    private boolean matchFound(I actual, E exp) {

        for (E act : actual) {
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
