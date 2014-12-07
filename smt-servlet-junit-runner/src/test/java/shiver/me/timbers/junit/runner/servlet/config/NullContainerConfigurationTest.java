package shiver.me.timbers.junit.runner.servlet.config;

import org.junit.Test;

import static shiver.me.timbers.junit.runner.servlet.config.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

public class NullContainerConfigurationTest {

    @SuppressWarnings("unchecked")
    @Test
    public void Null_container_config_does_nothing() {
        NULL_CONTAINER_CONFIG.configure(null);
    }
}
