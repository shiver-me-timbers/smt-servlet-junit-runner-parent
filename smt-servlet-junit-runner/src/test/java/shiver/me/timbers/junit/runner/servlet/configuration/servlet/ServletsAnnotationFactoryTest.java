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
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.mockAnnotatedServlets;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.mockEmptyServlets;

public class ServletsAnnotationFactoryTest {

    @Test
    @SuppressWarnings("unchecked")
    public void No_servlets_are_returned_if_none_are_configured() {

        // Given
        final Servlets expected = mockEmptyServlets();

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.servlets()).thenReturn(new Class[]{});

        // When
        final Servlets servlets = new ServletsAnnotationFactory().create(configuration);

        // Then
        assertThat(servlets, equalAll(expected));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Servlets_are_returned_if_some_are_configured() {

        // Given
        final Servlets expected = mockAnnotatedServlets();

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.servlets()).thenReturn(new Class[]{ServletOne.class, ServletTwo.class, ServletThree.class});

        // When
        final Servlets actual = new ServletsAnnotationFactory().create(configuration);

        // Then
        assertThat(actual, equalAll(expected));
    }
}
