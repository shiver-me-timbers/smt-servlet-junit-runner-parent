package shiver.me.timbers.junit.runner.servlet.test;

import org.hamcrest.Matcher;

/**
 * @author Karl Bennett
 */
public interface WithMatcher<I> {

    Matcher matcher(I expected);
}
