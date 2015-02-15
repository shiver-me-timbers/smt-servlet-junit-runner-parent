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

package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.mockAnnotatedFilters;

public class SettableFiltersTest {

    @Test
    public void An_array_of_filter_classes_can_be_used_for_creation() {

        // Given
        final Filters expected = mockAnnotatedFilters();

        // When
        final Filters actual = new SettableFilters(FilterOne.class, FilterTwo.class, FilterThree.class);

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void A_list_of_filter_details_can_be_used_for_creation() {

        // Given
        final Filters expected = mockAnnotatedFilters();

        // When
        final Filters actual = new SettableFilters(asList(new FilterDetail(FilterOne.class),
                new FilterDetail(FilterTwo.class), new FilterDetail(FilterThree.class)));

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void An_array_of_filters_can_be_used_for_creation() {

        // Given
        final Filters one = new SettableFilters(FilterOne.class);
        final Filters two = new SettableFilters(FilterTwo.class, FilterThree.class);
        final Filters expected = mockAnnotatedFilters();

        // When
        final Filters actual = new SettableFilters(one, two);

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void Filters_can_be_toStringed() {

        // Given
        final List<FilterDetail> list = asList(new FilterDetail(FilterOne.class), new FilterDetail(FilterTwo.class),
                new FilterDetail(FilterThree.class));
        final Filters filters = new SettableFilters(list);
        final String expected = format("SettableFilters { filters = '%s' }", list);

        // When
        final String actual = filters.toString();

        // Then
        assertEquals(expected, actual);
    }
}
