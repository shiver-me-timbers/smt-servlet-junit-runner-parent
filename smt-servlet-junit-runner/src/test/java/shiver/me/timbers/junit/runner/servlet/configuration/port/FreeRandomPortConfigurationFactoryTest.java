package shiver.me.timbers.junit.runner.servlet.configuration.port;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class FreeRandomPortConfigurationFactoryTest {

    @Test
    public void A_free_port_can_be_found() {

        final PortConfiguration portConfiguration = new FreeRandomPortConfigurationFactory().create();

        assertThat(portConfiguration.getPort(), greaterThan(0));
    }
}
