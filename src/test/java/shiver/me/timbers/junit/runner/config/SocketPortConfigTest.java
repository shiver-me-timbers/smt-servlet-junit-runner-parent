package shiver.me.timbers.junit.runner.config;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SocketPortConfigTest {

    @Test
    public void A_random_port_can_be_set() {

        assertThat(new SocketPortConfig().getPort(), greaterThan(0));
    }

    @Test
    public void A_specific_port_can_be_set() {

        final int port = 9998;

        assertEquals(port, new SocketPortConfig(port).getPort());
    }

    @Test(expected = RuntimeException.class)
    public void An_invalid_port_cannot_be_set() {

        new SocketPortConfig(80);  // Root privileges are required to use port 80.
    }
}
