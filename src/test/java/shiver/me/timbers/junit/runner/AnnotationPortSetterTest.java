package shiver.me.timbers.junit.runner;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.junit.runner.annotation.Port;
import shiver.me.timbers.junit.runner.config.PortConfig;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Karl Bennett
 */
public class AnnotationPortSetterTest {

    private int port;

    private PortConfig portConfig;

    @Before
    public void setUp() {

        port = 9999;

        portConfig = mock(PortConfig.class);
        when(portConfig.getPort()).thenReturn(port);
    }

    @Test
    public void The_port_number_is_set_on_an_annotated_field_of_the_right_type() {

        // Given
        final PortToBeSet test = new PortToBeSet();

        // When
        new AnnotationPortSetter().set(test, portConfig);

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
        new AnnotationPortSetter().set(test, portConfig);
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
        new AnnotationPortSetter().set(test, portConfig);

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

    class PortToBeSet {

        @Port
        private int port;

        int getPort() {
            return port;
        }
    }
}
