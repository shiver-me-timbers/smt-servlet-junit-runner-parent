package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * @author Karl Bennett
 */
public class EqualToWithMatcher<I> implements WithMatcher<I> {

    @Override
    public Matcher matcher(I expected) {
        return Matchers.equalTo(expected);
    }
}
