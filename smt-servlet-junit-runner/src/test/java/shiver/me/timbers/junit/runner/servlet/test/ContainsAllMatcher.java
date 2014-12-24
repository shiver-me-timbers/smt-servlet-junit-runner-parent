package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * This matcher can be used to check that two {@link Iterable}s are equal. The order of the elements is ignored.
 * <p/>
 * Additionally a matcher can be added using {@link #with} which be used to checking each element.
 * <p/>
 * The standard {@link org.hamcrest.Matchers#equalTo} method will be used otherwise.
 *
 * @author Karl Bennett
 */
public class ContainsAllMatcher<E, I extends Iterable<E>> extends TypeSafeMatcher<I> {

    public static <E, I extends Iterable<E>> ContainsAllMatcher<E, I> containsAll(I expected) {
        return new ContainsAllMatcher<>(expected);
    }

    private final I expected;
    private WithMatcher<E> with;

    public ContainsAllMatcher(I expected) {
        this.expected = expected;
        this.with = new EqualToWithMatcher<>();
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
            if (with.matcher(exp).matches(act)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }

    public ContainsAllMatcher<E, I> with(WithMatcher<E> with) {
        this.with = with;
        return this;
    }
}
