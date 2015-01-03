package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompositeContainerConfigurationAnnotationFactoryTest {

    @Test
    public void No_servlet_factories_can_be_used_to_create_no_servlets() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Object>, Object> appender = mock(Factory.class);

        final Object expected = new Object();

        // Given
        when(appender.create(eq(new ArrayList<>()))).thenReturn(expected);

        // When
        final Object actual = new CompositeContainerConfigurationAnnotationFactory<>(appender)
                .create(mock(ContainerConfiguration.class));

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void Multiple_servlet_factories_can_be_used_to_create_no_servlets() throws Exception {

        @SuppressWarnings("unchecked")
        final AnnotationFactory<ContainerConfiguration, Object> factoryOne = mock(AnnotationFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<ContainerConfiguration, Object> factoryTwo = mock(AnnotationFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<ContainerConfiguration, Object> factoryThree = mock(AnnotationFactory.class);
        @SuppressWarnings("unchecked")
        final Factory<List<Object>, Object> appender = mock(Factory.class);

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);

        final Object one = new Object();
        final Object two = new Object();
        final Object three = new Object();

        final Object expected = new Object();

        // Given
        when(factoryOne.create(configuration)).thenReturn(one);
        when(factoryTwo.create(configuration)).thenReturn(two);
        when(factoryThree.create(configuration)).thenReturn(three);
        when(appender.create(eq(asList(one, two, three)))).thenReturn(expected);

        // When
        final Object actual = new CompositeContainerConfigurationAnnotationFactory<>(appender, factoryOne, factoryTwo,
                factoryThree).create(configuration);

        // Then
        assertEquals(expected, actual);
    }
}
