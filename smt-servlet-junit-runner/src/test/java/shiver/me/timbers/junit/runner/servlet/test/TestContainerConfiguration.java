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

package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration;

import java.util.concurrent.atomic.AtomicReference;

public class TestContainerConfiguration implements ContainerConfiguration<TestServletContainer> {

    public static final AtomicReference<TestServletContainer> TEST_SERVLET_CONTAINER_REFERENCE =
            new AtomicReference<>();

    @Override
    public void configure(TestServletContainer container) {
        TEST_SERVLET_CONTAINER_REFERENCE.set(container);
    }
}
