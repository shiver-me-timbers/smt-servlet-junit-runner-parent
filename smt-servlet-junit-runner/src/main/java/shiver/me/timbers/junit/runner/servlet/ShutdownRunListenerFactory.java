package shiver.me.timbers.junit.runner.servlet;

import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Karl Bennett
 */
public class ShutdownRunListenerFactory implements RunListenerFactory {

    private final Logger log = LoggerFactory.getLogger(ShutdownRunListenerFactory.class);

    @Override
    public RunListener create(Container container) {
        log.debug("Creating {} with {}.", ShutdownRunListener.class, container);
        return new ShutdownRunListener(container);
    }
}
