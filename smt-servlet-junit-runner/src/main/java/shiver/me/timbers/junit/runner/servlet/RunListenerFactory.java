package shiver.me.timbers.junit.runner.servlet;

import org.junit.runner.notification.RunListener;

/**
 * This factory will return the servlet container {@link RunListener} that will be added to the JUnit lifecycle. This
 * will usually be used to shutdown any running servlet containers at the end of the test.
 */
public interface RunListenerFactory {

    RunListener create(Container container);
}
