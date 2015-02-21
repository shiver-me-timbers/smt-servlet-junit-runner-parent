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

package shiver.me.timbers.junit.runner.tomcat.filter;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static javax.servlet.DispatcherType.FORWARD;
import static javax.servlet.DispatcherType.INCLUDE;
import static javax.servlet.DispatcherType.REQUEST;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterDetailFilterMapTest {

    @Test
    public void A_filter_map_can_be_created_with_a_filter_detail() {

        final FilterDetail expected = mock(FilterDetail.class);

        // Given
        when(expected.getFilterName()).thenReturn("filterName");
        when(expected.getServletNames()).thenReturn(asList("servletNameOne", "servletNameTwo", "servletNameThree"));
        when(expected.getUrlPatterns()).thenReturn(asList("urlPatternOne", "urlPatternTwo", "urlPatternThree"));
        when(expected.getDispatcherTypes()).thenReturn(asList(FORWARD, INCLUDE, REQUEST));

        // When
        final FilterDetailFilterMap actual = new FilterDetailFilterMap(expected);

        // Then
        assertEquals(expected.getFilterName(), actual.getFilterName());
        assertEquals(expected.getServletNames(), asList(actual.getServletNames()));
        assertEquals(expected.getUrlPatterns(), asList(actual.getURLPatterns()));
        assertEquals(transform(expected.getDispatcherTypes()), asList(actual.getDispatcherNames()));
    }

    private static List<String> transform(List<DispatcherType> dispatcherTypes) {

        final List<String> dispatcherNames = new ArrayList<>();

        for (DispatcherType dispatcherType : dispatcherTypes) {
            dispatcherNames.add(dispatcherType.name());
        }

        return dispatcherNames;
    }
}
