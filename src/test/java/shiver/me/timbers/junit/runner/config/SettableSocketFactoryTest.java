package shiver.me.timbers.junit.runner.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.config.NullSocketConfig.NULL_PORT_CONFIG;

public class SettableSocketFactoryTest {

    private static final Object TEST = new Object();
    private static final SocketConfig EXPECTED = mock(SocketConfig.class);

    @Test
    public void The_port_is_set_when_provided() {

        // Given
        final StaticSocketConfigFactory staticPortConfigFactory = mock(StaticSocketConfigFactory.class);
        when(staticPortConfigFactory.create(TEST)).thenReturn(EXPECTED);

        // When
        final SocketConfig actual = new SettableSocketConfigFactory(staticPortConfigFactory, null).create(TEST);

        // Then
        assertEquals(EXPECTED, actual);
    }

    @Test
    public void The_port_is_randomly_set_if_none_is_provided() {

        // Given
        final StaticSocketConfigFactory staticPortConfigFactory = mock(StaticSocketConfigFactory.class);
        when(staticPortConfigFactory.create(TEST)).thenReturn(NULL_PORT_CONFIG);

        final RandomPortConfigFactory randomPortConfigFactory = mock(RandomPortConfigFactory.class);
        when(randomPortConfigFactory.create()).thenReturn(EXPECTED);

        // When
        final SocketConfig actual = new SettableSocketConfigFactory(staticPortConfigFactory,
                randomPortConfigFactory).create(TEST);

        // Then
        assertEquals(EXPECTED, actual);
    }
}
