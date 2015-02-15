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

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Packages;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PackagesAnnotationFactoryTest {

    @Test
    public void Servlets_are_returned_when_packages_are_supplied() throws Exception {

        @SuppressWarnings("unchecked")
        final PackagesFactory<Object> packagesFactory = mock(PackagesFactory.class);

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);

        final Object expected = Object.class;
        final String[] packageStrings = {};

        // Given
        when(configuration.packages()).thenReturn(packageStrings);
        when(packagesFactory.create(any(Packages.class))).thenReturn(expected);

        // When
        final Object actual = new PackagesAnnotationFactory<>(packagesFactory).create(configuration);

        // Then
        assertEquals(expected, actual);
    }
}
