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

package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.mockAnnotatedFilters;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.mockEmptyFilters;

public class FiltersAnnotationFactoryTest {

    @Test
    @SuppressWarnings("unchecked")
    public void No_filters_are_returned_if_none_are_configured() {

        // Given
        final Filters expected = mockEmptyFilters();

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.filters()).thenReturn(new Class[]{});

        // When
        final Filters filters = new FiltersAnnotationFactory().create(configuration);

        // Then
        assertThat(filters, equalAll(expected));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Filters_are_returned_if_some_are_configured() {

        // Given
        final Filters expected = mockAnnotatedFilters();

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.filters()).thenReturn(new Class[]{FilterOne.class, FilterTwo.class, FilterThree.class});

        // When
        final Filters filters = new FiltersAnnotationFactory().create(configuration);

        // Then
        assertThat(filters, equalAll(expected));
    }
}
