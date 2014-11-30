package shiver.me.timbers.junit.runner;

/**
 * This factory will find any servlet configuration that has been set on the test and return it ready to be applied
 * before the servlet container is started.
 *
 * @author Karl Bennett
 */
public interface ServletsFactory {

    Servlets create(Object target);
}
