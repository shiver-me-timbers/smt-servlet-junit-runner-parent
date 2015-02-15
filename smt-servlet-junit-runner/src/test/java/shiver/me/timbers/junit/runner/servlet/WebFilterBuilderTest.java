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
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import static javax.servlet.DispatcherType.FORWARD;
import static javax.servlet.DispatcherType.REQUEST;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class WebFilterBuilderTest {

    @Test
    public void A_web_filter_can_be_built() {
        // Given
        final String description = "description";
        final String displayName = "displayName";
        final WebInitParam initParams = mock(WebInitParam.class);
        final String filterName = "filterName";
        final String smallIcon = "smallIcon";
        final String largeIcon = "largeIcon";
        final String servletNames = "servletNames";
        final String value = "value";
        final String urlPatterns = "urlPatterns";
        final DispatcherType dispatcherTypes = FORWARD;
        final boolean asyncSupported = true;

        // When
        final WebFilter actual = WebFilterBuilder.create()
                .withDescription(description)
                .withDisplayName(displayName)
                .withInitParams(initParams)
                .withFilterName(filterName)
                .withSmallIcon(smallIcon)
                .withLargeIcon(largeIcon)
                .withValue(value)
                .withServletNames(servletNames)
                .withUrlPatterns(urlPatterns)
                .withDispatcherTypes(dispatcherTypes)
                .withAsyncSupported(asyncSupported)
                .build();

        // Then
        assertEquals(description, actual.description());
        assertEquals(displayName, actual.displayName());
        assertArrayEquals(new WebInitParam[]{initParams}, actual.initParams());
        assertEquals(filterName, actual.filterName());
        assertEquals(smallIcon, actual.smallIcon());
        assertEquals(largeIcon, actual.largeIcon());
        assertArrayEquals(new String[]{servletNames}, actual.servletNames());
        assertArrayEquals(new String[]{value}, actual.value());
        assertArrayEquals(new String[]{urlPatterns}, actual.urlPatterns());
        assertArrayEquals(new DispatcherType[]{dispatcherTypes}, actual.dispatcherTypes());
        assertEquals(asyncSupported, actual.asyncSupported());
        assertEquals(WebFilter.class, actual.annotationType());
    }

    @Test
    public void A_default_web_filter_can_be_built() {

        // When
        final WebFilter actual = WebFilterBuilder.create().build();

        // Then
        assertThat(actual.description(), isEmptyString());
        assertThat(actual.displayName(), isEmptyString());
        assertArrayEquals(new WebInitParam[0], actual.initParams());
        assertThat(actual.filterName(), isEmptyString());
        assertThat(actual.smallIcon(), isEmptyString());
        assertThat(actual.largeIcon(), isEmptyString());
        assertArrayEquals(new String[0], actual.servletNames());
        assertArrayEquals(new String[0], actual.value());
        assertArrayEquals(new String[0], actual.urlPatterns());
        assertArrayEquals(new DispatcherType[]{REQUEST}, actual.dispatcherTypes());
        assertFalse(actual.asyncSupported());
        assertEquals(WebFilter.class, actual.annotationType());
    }
}
