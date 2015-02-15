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

package shiver.me.timbers.junit.runner.servlet.annotation;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

/**
 * This annotation is used to configure a {@link Servlet} class that hasn't already been configured. It can also be used
 * to override the configuration of a {@code Servlet} that has already been configured.
 *
 * @author Karl Bennett
 */
public @interface ServletConfiguration {

    /**
     * The configuration for the {@code Servlet} that should be loaded for this test.
     */
    WebServlet configuration();

    /**
     * The {@code Servlet} that should be loaded for this test.
     */
    Class<? extends Servlet> servlet();
}
