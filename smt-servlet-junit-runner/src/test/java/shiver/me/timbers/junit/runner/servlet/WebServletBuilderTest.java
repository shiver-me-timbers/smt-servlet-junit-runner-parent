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

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class WebServletBuilderTest {

    @Test
    public void A_web_servlet_can_be_built() {
        // Given
        final String name = "name";
        final String value = "value";
        final String urlPattern = "urlPatterns";
        final int loadOnStartup = 1;
        final WebInitParam initParams = mock(WebInitParam.class);
        final boolean asyncSupported = true;
        final String smallIcon = "smallIcon";
        final String largeIcon = "largeIcon";
        final String description = "description";
        final String displayName = "displayName";

        // When
        final WebServlet actual = WebServletBuilder.create()
                .withName(name)
                .withValue(value)
                .withUrlPatterns(urlPattern)
                .withLoadOnStartup(loadOnStartup)
                .withInitParams(initParams)
                .withAsyncSupported(asyncSupported)
                .withSmallIcon(smallIcon)
                .withLargeIcon(largeIcon)
                .withDescription(description)
                .withDisplayName(displayName)
                .build();

        // Then
        assertEquals(name, actual.name());
        assertArrayEquals(new String[]{value}, actual.value());
        assertArrayEquals(new String[]{urlPattern}, actual.urlPatterns());
        assertEquals(loadOnStartup, actual.loadOnStartup());
        assertArrayEquals(new WebInitParam[]{initParams}, actual.initParams());
        assertEquals(asyncSupported, actual.asyncSupported());
        assertEquals(smallIcon, actual.smallIcon());
        assertEquals(largeIcon, actual.largeIcon());
        assertEquals(description, actual.description());
        assertEquals(displayName, actual.displayName());
        assertEquals(WebServlet.class, actual.annotationType());
    }

    @Test
    public void A_default_web_servlet_can_be_built() {

        // When
        final WebServlet actual = WebServletBuilder.create().build();

        // Then
        assertThat(actual.name(), isEmptyString());
        assertArrayEquals(new String[0], actual.value());
        assertArrayEquals(new String[0], actual.urlPatterns());
        assertEquals(-1, actual.loadOnStartup());
        assertArrayEquals(new WebInitParam[0], actual.initParams());
        assertFalse(actual.asyncSupported());
        assertThat(actual.smallIcon(), isEmptyString());
        assertThat(actual.largeIcon(), isEmptyString());
        assertThat(actual.description(), isEmptyString());
        assertThat(actual.displayName(), isEmptyString());
        assertEquals(WebServlet.class, actual.annotationType());
    }
}
