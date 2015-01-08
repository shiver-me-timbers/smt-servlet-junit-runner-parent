package shiver.me.timbers.junit.runner.servlet;

public interface Equal<T> {

    boolean equal(T left, Object right);
}
