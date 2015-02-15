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

package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class MethodContainerConfiguration<C> implements ContainerConfiguration<C> {

    private final Logger log = LoggerFactory.getLogger(MethodContainerConfiguration.class);

    private final Method method;
    private final Object target;

    public MethodContainerConfiguration(Method method, Object target) {
        this.method = method;
        this.target = target;

        log.debug("Constructed");
    }

    @Override
    public void configure(C container) {

        try {
            log.debug("Invoking {}'s {} method on {}", target, method, container);
            method.invoke(target, container);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(
                    format("The @ContainerConfiguration method (%s) must be public and only have a single argument " +
                            "that is of the type of the containers configuration class.",
                            method.getName()),
                    e
            );
        }
    }
}
