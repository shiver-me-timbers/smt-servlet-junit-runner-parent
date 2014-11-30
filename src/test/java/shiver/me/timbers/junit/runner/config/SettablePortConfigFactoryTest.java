package shiver.me.timbers.junit.runner.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.config.NullPortConfig.NULL_PORT_CONFIG;

public class SettablePortConfigFactoryTest {

    private static final Object TEST = new Object();
    private static final PortConfig EXPECTED = mock(PortConfig.class);

    @Test
    public void The_port_is_set_when_provided() {

        // Given
        final StaticPortConfigFactory staticPortConfigFactory = mock(StaticPortConfigFactory.class);
        when(staticPortConfigFactory.create(TEST)).thenReturn(EXPECTED);

        // When
        final PortConfig actual = new SettablePortConfigFactory(staticPortConfigFactory, null).create(TEST);

        // Then
        assertEquals(EXPECTED, actual);
    }

    @Test
    public void The_port_is_randomly_set_if_none_is_provided() {

        // Given
        final StaticPortConfigFactory staticPortConfigFactory = mock(StaticPortConfigFactory.class);
        when(staticPortConfigFactory.create(TEST)).thenReturn(NULL_PORT_CONFIG);

        final RandomPortConfigFactory randomPortConfigFactory = mock(RandomPortConfigFactory.class);
        when(randomPortConfigFactory.create()).thenReturn(EXPECTED);

        // When
        final PortConfig actual = new SettablePortConfigFactory(staticPortConfigFactory,
                randomPortConfigFactory).create(TEST);

        // Then
        assertEquals(EXPECTED, actual);
    }
}
