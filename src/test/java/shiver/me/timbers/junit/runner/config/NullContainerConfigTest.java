package shiver.me.timbers.junit.runner.config;

import org.junit.Test;

import static shiver.me.timbers.junit.runner.config.NullContainerConfig.NULL_CONTAINER_CONFIG;

public class NullContainerConfigTest {

    @SuppressWarnings("unchecked")
    @Test
    public void Null_container_config_does_nothing() {
        NULL_CONTAINER_CONFIG.configure(null);
    }
}
