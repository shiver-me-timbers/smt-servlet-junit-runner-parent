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
import org.junit.runner.RunWith;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.test.TestAnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.servlet.test.TestServletContainer;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.mockEmptyServlets;

@RunWith(TestAnnotationServletJUnitRunner.class)
public class MethodLevelAnnotationServletJUnitRunnerTest {

    private TestServletContainer container;

    @ContainerConfiguration
    public void config(TestServletContainer container) {
        this.container = container;
    }

    @Port
    private int port;

    @Test
    public void Configuration_at_the_method_level_should_work() {

        // Given
        final Servlets servlets = mockEmptyServlets();

        // Then
        assertThat(port, greaterThan(0));
        assertEquals(container.getPort(), port);
        assertThat(container.getServlets(), equalAll(servlets));
        assertTrue(container.isStarted());
    }
}
