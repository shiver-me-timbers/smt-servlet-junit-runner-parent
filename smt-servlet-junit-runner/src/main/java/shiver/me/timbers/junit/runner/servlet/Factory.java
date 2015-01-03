package shiver.me.timbers.junit.runner.servlet;

/**
 * @author Karl Bennett
 */
public interface Factory<I, O> {

    O create(I input);
}
