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

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ClassWebServletTest {

    @Test
    public void A_servlet_class_can_be_used_for_creation() {
        // When
        final ClassWebServlet actual = new ClassWebServlet(Servlet.class);

        // Then
        assertEquals(Servlet.class.getSimpleName(), actual.name());
        assertArrayEquals(new String[]{"/" + Servlet.class.getSimpleName()}, actual.value());
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
