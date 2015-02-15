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

package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.mockAnnotatedServlets;

/**
 * @author Karl Bennett
 */
public class ServletServletsFactoryTest {

    @Test
    public void An_array_of_servlet_classes_can_be_used_for_creation() {

        // Given
        final List<Class<? extends Servlet>> types = Arrays.<Class<? extends Servlet>>asList(
                ServletOne.class,
                ServletTwo.class,
                ServletThree.class
        );
        final Servlets expected = mockAnnotatedServlets();

        // When
        final Servlets actual = new ServletServletsFactory().create(types);

        // Then
        assertThat(actual, equalAll(expected));
    }
}
