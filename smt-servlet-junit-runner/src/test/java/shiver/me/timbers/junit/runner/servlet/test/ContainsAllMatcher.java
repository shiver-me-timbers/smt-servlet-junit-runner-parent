package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.LinkedList;
import java.util.List;

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

        final List<Object> processingActual = toList(actual);

        for (Object exp : expected) {

            if (!matchFound(processingActual, exp)) {
                return false;
            }

            processingActual.remove(exp);
        }

        return true;
    }

    private boolean matchFound(List<Object> actual, Object exp) {

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

    private static List<Object> toList(Iterable expected) {

        final List<Object> list = new LinkedList<>();

        for (Object element : expected) {
            list.add(element);
        }

        return list;
    }
}
