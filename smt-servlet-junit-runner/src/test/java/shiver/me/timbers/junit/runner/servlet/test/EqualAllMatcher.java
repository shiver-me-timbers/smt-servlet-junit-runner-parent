package shiver.me.timbers.junit.runner.servlet.test;

/**
 * This matcher can be used to check that two {@link Iterable}s contain the same elements. The order of the elements is
 * ignored.
 *
 * @author Karl Bennett
 */
public class EqualAllMatcher<I extends Iterable> extends ContainsAllMatcher<I> {

    public static <I extends Iterable> EqualAllMatcher<I> equalAll(I expected) {
        return new EqualAllMatcher<>(expected);
    }

    private final I expected;

    public EqualAllMatcher(I expected) {
        super(expected);
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(I actual) {

        int expectedCount = 0;
        int actualCount = 0;

        for (Object e : expected) {
            expectedCount++;
        }

        for (Object e : actual) {
            actualCount++;
        }

        if (expectedCount != actualCount) {
            return false;
        }

        return super.matchesSafely(actual);
    }
}
