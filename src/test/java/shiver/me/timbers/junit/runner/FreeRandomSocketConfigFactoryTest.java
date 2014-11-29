package shiver.me.timbers.junit.runner;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class FreeRandomSocketConfigFactoryTest {

    @Test
    public void A_free_port_can_be_found() {

        final SocketConfig socketConfig = new FreeRandomPortConfigFactory().create();

        assertThat(socketConfig.getSocket().getLocalPort(), greaterThan(0));
    }
}
