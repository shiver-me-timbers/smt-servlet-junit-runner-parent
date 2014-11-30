package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.notification.RunListener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ShutdownRunListenerFactoryTest {

    @Test
    public void A_shutdown_run_listener_is_created() throws Exception {

        // Given
        final Container container = mock(Container.class);

        // When
        final RunListener listener = new ShutdownRunListenerFactory().create(container);
        listener.testRunFinished(null);

        // Then
        verify(container).shutdown();
    }
}
