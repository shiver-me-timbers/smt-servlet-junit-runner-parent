package shiver.me.timbers.junit.runner.servlet;

/**
 * @author Karl Bennett
 */
public class NullEmptyFactory<T> implements EmptyFactory<T> {

    @Override
    public T create() {
        return null;
    }
}
