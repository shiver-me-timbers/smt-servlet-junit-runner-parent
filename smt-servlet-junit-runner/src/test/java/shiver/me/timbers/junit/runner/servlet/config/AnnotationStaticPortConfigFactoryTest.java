package shiver.me.timbers.junit.runner.servlet.config;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;

import static org.junit.Assert.assertEquals;
import static shiver.me.timbers.junit.runner.servlet.config.NullPortConfig.NULL_PORT_CONFIG;

public class AnnotationStaticPortConfigFactoryTest {

    private static final int PORT = 9999;

    @Test
    public void A_port_config_is_not_returned_if_no_port_is_set() {

        class TestClass {
        }

        assertEquals(NULL_PORT_CONFIG, new AnnotationStaticPortConfigFactory().create(new TestClass()));
    }

    @Test
    public void A_port_config_is_returned_if_a_port_is_set() {

        @Port(PORT)
        class TestClass {
        }

        final PortConfig config = new AnnotationStaticPortConfigFactory().create(new TestClass());

        assertEquals(PORT, config.getPort());
    }
}
