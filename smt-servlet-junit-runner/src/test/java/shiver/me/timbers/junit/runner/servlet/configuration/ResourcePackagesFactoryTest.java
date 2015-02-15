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
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Packages;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourcePackagesFactoryTest {

    @Test
    public void A_resource_is_returned_from_a_package() throws Exception {

        final ClassPathsFactory classPathsFactory = mock(ClassPathsFactory.class);
        @SuppressWarnings("unchecked")
        final ClassPathsConverter<FilterInput> classPathsConverter = mock(ClassPathsConverter.class);
        @SuppressWarnings("unchecked")
        final Factory<FilterInput, FilterOutput> filter = mock(Factory.class);
        @SuppressWarnings("unchecked")
        final Factory<FilterOutput, Output> converter = mock(Factory.class);

        final Packages packages = mock(Packages.class);

        @SuppressWarnings("unchecked")
        final List<String> list = mock(List.class);
        final FilterInput filterInput = mock(FilterInput.class);
        final FilterOutput filterOutput = mock(FilterOutput.class);
        final Output expected = mock(Output.class);

        // Given
        when(classPathsFactory.create(packages)).thenReturn(list);
        when(classPathsConverter.create(list)).thenReturn(filterInput);
        when(filter.create(filterInput)).thenReturn(filterOutput);
        when(converter.create(filterOutput)).thenReturn(expected);

        // When
        final Object actual = new ResourcePackagesFactory<>(
                classPathsFactory,
                classPathsConverter,
                filter,
                converter
        ).create(packages);

        // Then
        assertEquals(expected, actual);
    }

    private interface FilterInput {
    }

    private interface FilterOutput {
    }

    private interface Output {
    }
}
