/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.servlet.configuration.port;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.configuration.port.NullPortConfiguration.NULL_PORT_CONFIG;

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

        final RandomPortConfigurationFactory randomPortConfigurationFactory = mock(RandomPortConfigurationFactory.class);
        when(randomPortConfigurationFactory.create()).thenReturn(EXPECTED);

        // When
        final PortConfiguration actual = new SettablePortConfigurationFactory(staticPortConfigFactory,
                randomPortConfigurationFactory).create(TEST);

        // Then
        assertEquals(EXPECTED, actual);
    }
}
