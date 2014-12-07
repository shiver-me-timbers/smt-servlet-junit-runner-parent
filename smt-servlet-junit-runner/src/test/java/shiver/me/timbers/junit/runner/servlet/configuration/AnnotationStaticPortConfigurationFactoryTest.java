package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import static org.junit.Assert.assertEquals;
import static shiver.me.timbers.junit.runner.servlet.configuration.NullPortConfiguration.NULL_PORT_CONFIG;

public class AnnotationStaticPortConfigurationFactoryTest {

    private static final int PORT = 9999;

    @Test
    public void A_port_configuration_is_not_returned_if_no_config_is_set() {

        class TestClass {
        }

        assertEquals(NULL_PORT_CONFIG, new AnnotationStaticPortConfigurationFactory().create(new TestClass()));
    }

    @Test
    public void A_port_configuration_is_not_returned_if_no_port_is_set() {

        @ContainerConfiguration
        class TestClass {
        }

        assertEquals(NULL_PORT_CONFIG, new AnnotationStaticPortConfigurationFactory().create(new TestClass()));
    }

    @Test
    public void A_port_configuration_is_returned_if_a_port_is_set() {

        @ContainerConfiguration(port = PORT)
        class TestClass {
        }

        final PortConfiguration config = new AnnotationStaticPortConfigurationFactory().create(new TestClass());

        assertEquals(PORT, config.getPort());
    }
}
