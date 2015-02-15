package shiver.me.timbers.junit.runner.servlet;

import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Karl Bennett
 */
public class ShutdownRunListener extends RunListener {

    private final Logger log = LoggerFactory.getLogger(ShutdownRunListener.class);

    private final Container container;

    public ShutdownRunListener(Container container) {
        this.container = container;

        log.debug("Constructed");
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        log.debug("Shutting down container");
        container.shutdown();
    }
}
