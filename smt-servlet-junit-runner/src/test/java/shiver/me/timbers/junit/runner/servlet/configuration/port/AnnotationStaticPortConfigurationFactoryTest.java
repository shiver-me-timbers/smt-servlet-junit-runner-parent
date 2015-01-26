package shiver.me.timbers.junit.runner.servlet.configuration.port;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.configuration.port.NullPortConfiguration.NULL_PORT_CONFIG;

public class AnnotationStaticPortConfigurationFactoryTest {

    @Test
    public void A_port_configuration_is_not_returned_if_no_config_is_set() {

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<ContainerConfiguration> annotationExtractor = mock(AnnotationExtractor.class);

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(null);

        // When
        final PortConfiguration actual = new AnnotationStaticPortConfigurationFactory(annotationExtractor)
                .create(new TestClass());

        // Then
        assertEquals(NULL_PORT_CONFIG, actual);
    }

    @Test
    public void A_port_configuration_is_not_returned_if_no_port_is_set() {

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<ContainerConfiguration> annotationExtractor = mock(AnnotationExtractor.class);
        final ContainerConfiguration containerConfiguration = mock(ContainerConfiguration.class);

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(containerConfiguration);
        when(containerConfiguration.port()).thenReturn(-1);

        // When
        final PortConfiguration actual = new AnnotationStaticPortConfigurationFactory(annotationExtractor)
                .create(new TestClass());

        // Then
        assertEquals(NULL_PORT_CONFIG, actual);
    }

    @Test
    public void A_port_configuration_is_returned_if_a_port_is_set() {

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<ContainerConfiguration> annotationExtractor = mock(AnnotationExtractor.class);
        final ContainerConfiguration containerConfiguration = mock(ContainerConfiguration.class);

        final int port = 9999;

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(containerConfiguration);
        when(containerConfiguration.port()).thenReturn(port);

        // When
        final PortConfiguration config = new AnnotationStaticPortConfigurationFactory(annotationExtractor)
                .create(new TestClass());

        // Then
        assertEquals(port, config.getPort());
    }

    private static class TestClass {
    }
}
