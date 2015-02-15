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

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import static javax.servlet.DispatcherType.REQUEST;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ClassWebFilterTest {

    @Test
    public void A_filter_class_can_be_used_for_creation() {
        // When
        final ClassWebFilter actual = new ClassWebFilter(Filter.class);

        // Then
        assertThat(actual.description(), isEmptyString());
        assertThat(actual.displayName(), isEmptyString());
        assertArrayEquals(new WebInitParam[0], actual.initParams());
        assertEquals(Filter.class.getSimpleName(), actual.filterName());
        assertThat(actual.smallIcon(), isEmptyString());
        assertThat(actual.largeIcon(), isEmptyString());
        assertArrayEquals(new String[0], actual.servletNames());
        assertArrayEquals(new String[]{"/" + Filter.class.getSimpleName()}, actual.value());
        assertArrayEquals(new String[0], actual.urlPatterns());
        assertArrayEquals(new DispatcherType[]{REQUEST}, actual.dispatcherTypes());
        assertFalse(actual.asyncSupported());
        assertEquals(WebFilter.class, actual.annotationType());
    }
}
