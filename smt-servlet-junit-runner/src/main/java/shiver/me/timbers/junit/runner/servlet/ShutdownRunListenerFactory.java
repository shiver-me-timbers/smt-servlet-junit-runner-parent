package shiver.me.timbers.junit.runner.servlet;

import org.junit.runner.notification.RunListener;

/**
 * @author Karl Bennett
 */
public class ShutdownRunListenerFactory implements RunListenerFactory {

    @Override
    public RunListener create(Container container) {

        return new ShutdownRunListener(container);
    }
}
