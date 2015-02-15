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

package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

public class ConsecutiveContainerConfigurationFactoryTest {

    private static final Object TEST = new Object();

    private ContainerConfigurationFactory nullConfig;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        nullConfig = mock(ContainerConfigurationFactory.class);
        when(nullConfig.create(TEST)).thenReturn(NULL_CONTAINER_CONFIG);
    }

    @Test
    public void A_null_container_config_is_returned_if_no_container_config_factories_supplied() {

        // When
        final ContainerConfiguration<Object> actual = new ConsecutiveContainerConfigurationFactory<>().create(TEST);

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_null_container_config_is_returned_if_the_supplied_container_config_factories() {

        // When
        final ContainerConfiguration<Object> actual = new ConsecutiveContainerConfigurationFactory<Object>(nullConfig, nullConfig,
                nullConfig).create(TEST);

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void A_container_config_is_returned_if_a_supplied_container_config_factory_returns_one() {

        // Given
        final ContainerConfiguration expected = mock(ContainerConfiguration.class);

        final ContainerConfigurationFactory configFactory = mock(ContainerConfigurationFactory.class);
        when(configFactory.create(TEST)).thenReturn(expected);

        // When
        final ContainerConfiguration<Object> actual = new ConsecutiveContainerConfigurationFactory<Object>(nullConfig, configFactory,
                nullConfig).create(TEST);

        // Then
        assertEquals(expected, actual);
    }
}