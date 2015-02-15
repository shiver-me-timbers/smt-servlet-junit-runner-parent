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

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnnotationPortSetterTest {

    private int port;

    private PortConfiguration portConfiguration;

    @Before
    public void setUp() {

        port = 9999;

        portConfiguration = mock(PortConfiguration.class);
        when(portConfiguration.getPort()).thenReturn(port);
    }

    @Test
    public void The_port_number_is_set_on_an_annotated_field_of_the_right_type() {

        // Given
        final PortToBeSet test = new PortToBeSet();

        // When
        new AnnotationPortSetter().set(test, portConfiguration);

        // Then
        assertEquals(port, test.getPort());
    }

    @Test
    public void The_port_number_is_set_on_an_inherited_annotated_field_of_the_right_type() {

        // Given
        final InheritedPortToBeSet test = new InheritedPortToBeSet();

        // When
        new AnnotationPortSetter().set(test, portConfiguration);

        // Then
        assertEquals(port, test.getPort());
    }

    @Test(expected = IllegalStateException.class)
    public void The_port_number_fails_to_be_set_for_an_annotated_field_of_the_wrong_type() {

        // Given
        class WrongType {

            @Port
            private String port;
        }
        final WrongType test = new WrongType();

        // When
        new AnnotationPortSetter().set(test, portConfiguration);
    }

    @Test
    public void The_port_number_is_not_set_for_an_unannotated_field() {

        // Given
        class NotAnnotated {

            private Integer port;

            Integer getPort() {
                return port;
            }
        }
        final NotAnnotated test = new NotAnnotated();

        // When
        new AnnotationPortSetter().set(test, portConfiguration);

        // Then
        assertNull(test.getPort());
    }

    @Test(expected = IllegalStateException.class)
    public void The_port_number_fails_to_be_set_for_an_inaccessible_filed() throws NoSuchFieldException {

        // Given
        final PortToBeSet test = new PortToBeSet();
        final Field field = test.getClass().getDeclaredField("port");

        // When
        AnnotationPortSetter.setPort(test, field, port);
    }

    private class PortToBeSet {

        @Port
        private int port;

        int getPort() {
            return port;
        }
    }

    private class InheritedPortToBeSet extends PortToBeSet {
    }
}
