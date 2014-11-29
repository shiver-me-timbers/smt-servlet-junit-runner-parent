package shiver.me.timbers.junit.runner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static shiver.me.timbers.junit.runner.NullSocketConfig.NULL_PORT_CONFIG;

public class AnnotationStaticSocketConfigFactoryTest {

    private static final int PORT = 9999;

    @Test
    public void A_port_config_is_not_returned_if_no_port_is_set() {

        assertEquals(NULL_PORT_CONFIG, new AnnotationStaticSocketConfigFactory().create(new NoPortSet()));
    }

    @Test
    public void A_port_config_is_returned_if_a_port_is_set() {

        final SocketConfig config = new AnnotationStaticSocketConfigFactory().create(new PortSet());

        assertEquals(PORT, config.getSocket().getLocalPort());
    }

    private static class NoPortSet {
    }

    @Port(PORT)
    private static class PortSet {
    }
}
