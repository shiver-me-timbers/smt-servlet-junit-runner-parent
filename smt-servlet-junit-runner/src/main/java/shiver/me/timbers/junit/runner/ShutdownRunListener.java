package shiver.me.timbers.junit.runner;

import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/**
 * @author Karl Bennett
 */
public class ShutdownRunListener extends RunListener {

    private final Container container;

    public ShutdownRunListener(Container container) {
        this.container = container;
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        container.shutdown();
    }
}
