package shiver.me.timbers.junit.runner.config;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class FreeRandomPortConfigFactoryTest {

    @Test
    public void A_free_port_can_be_found() {

        final PortConfig portConfig = new FreeRandomPortConfigFactory().create();

        assertThat(portConfig.getPort(), greaterThan(0));
    }
}
