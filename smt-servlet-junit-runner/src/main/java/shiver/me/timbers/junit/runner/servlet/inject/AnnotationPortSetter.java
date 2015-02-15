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

package shiver.me.timbers.junit.runner.servlet.inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;

import java.lang.reflect.Field;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class AnnotationPortSetter implements PortSetter {

    private final Logger log = LoggerFactory.getLogger(AnnotationPortSetter.class);

    @Override
    public void set(Object target, PortConfiguration portConfiguration) {

        final Class<?> type = target.getClass();

        final Field field = findPortField(type);

        final int port = portConfiguration.getPort();

        if (null == field) {
            log.warn("Test class {} does not have any field annotated @Port. Port {} has not been injected", target,
                    port);
            return;
        }

        field.setAccessible(true);

        log.debug("Injecting test class {}'s field {} with port {}", target, field, port);
        setPort(target, field, port);
    }

    private static Field findPortField(Class<?> type) {

        final Field[] fields = type.getDeclaredFields();

        for (Field field : fields) {

            if (null != field.getAnnotation(Port.class)) {
                return field;
            }
        }

        if (Object.class.equals(type)) {
            return null;
        }

        return findPortField(type.getSuperclass());
    }

    static void setPort(Object target, Field field, int port) {
        try {
            field.set(target, port);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(
                    format("The field (%s) must have the type int or Integer.", field.getName()),
                    e
            );
        }
    }
}
