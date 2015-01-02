package shiver.me.timbers.junit.runner.servlet.configuration.port;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static shiver.me.timbers.junit.runner.servlet.configuration.port.NullPortConfiguration.NULL_PORT_CONFIG;

public class NullPortConfigurationTest {

    @Test
    public void The_null_port_config_returns_a_null_socket() {

        assertEquals(-1, NULL_PORT_CONFIG.getPort());
    }
}
