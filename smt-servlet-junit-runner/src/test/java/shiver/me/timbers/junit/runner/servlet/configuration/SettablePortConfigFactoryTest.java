package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.configuration.NullPortConfiguration.NULL_PORT_CONFIG;

public class SettablePortConfigFactoryTest {

    private static final Object TEST = new Object();
    private static final PortConfiguration EXPECTED = mock(PortConfiguration.class);

    @Test
    public void The_port_is_set_when_provided() {

        // Given
        final StaticPortConfigurationFactory staticPortConfigFactory = mock(StaticPortConfigurationFactory.class);
        when(staticPortConfigFactory.create(TEST)).thenReturn(EXPECTED);

        // When
        final PortConfiguration actual = new SettablePortConfigurationFactory(staticPortConfigFactory, null).create(TEST);

        // Then
        assertEquals(EXPECTED, actual);
    }

    @Test
    public void The_port_is_randomly_set_if_none_is_provided() {

        // Given
        final StaticPortConfigurationFactory staticPortConfigFactory = mock(StaticPortConfigurationFactory.class);
        when(staticPortConfigFactory.create(TEST)).thenReturn(NULL_PORT_CONFIG);

        final RandomPortConfigFactory randomPortConfigFactory = mock(RandomPortConfigFactory.class);
        when(randomPortConfigFactory.create()).thenReturn(EXPECTED);

        // When
        final PortConfiguration actual = new SettablePortConfigurationFactory(staticPortConfigFactory,
                randomPortConfigFactory).create(TEST);

        // Then
        assertEquals(EXPECTED, actual);
    }
}
