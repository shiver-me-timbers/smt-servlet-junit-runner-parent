package shiver.me.timbers.junit.runner.config;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static shiver.me.timbers.junit.runner.config.NullSocketConfig.NULL_PORT_CONFIG;

public class NullPortConfigTest {

    @Test
    public void The_null_port_config_returns_a_null_socket() {

        assertNull(NULL_PORT_CONFIG.getSocket());
    }
}
