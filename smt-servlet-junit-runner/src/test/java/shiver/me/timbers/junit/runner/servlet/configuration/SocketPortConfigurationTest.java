package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SocketPortConfigurationTest {

    @Test
    public void A_random_port_can_be_set() {

        assertThat(new SocketPortConfiguration().getPort(), greaterThan(0));
    }

    @Test
    public void A_specific_port_can_be_set() {

        final int port = 9998;

        assertEquals(port, new SocketPortConfiguration(port).getPort());
    }

    @Test(expected = RuntimeException.class)
    public void An_invalid_port_cannot_be_set() {

        new SocketPortConfiguration(80);  // Root privileges are required to use port 80.
    }
}
