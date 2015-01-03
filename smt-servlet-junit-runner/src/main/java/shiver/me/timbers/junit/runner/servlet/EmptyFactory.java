package shiver.me.timbers.junit.runner.servlet;

/**
 * This factory is used when an instance is required with no input.
 *
 * @author Karl Bennett
 */
public interface EmptyFactory<T> {
    T create();
}
