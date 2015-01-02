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
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyServlets;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockServlets;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;

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
        assertThat(servlets, equalTo(expected));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Servlets_are_returned_if_some_are_configured() {

        // Given
        final Servlets expected = mockServlets();

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.servlets()).thenReturn(new Class[]{ServletOne.class, ServletTwo.class, ServletThree.class});

        // When
        final Servlets actual = new ServletsAnnotationFactory().create(configuration);

        // Then
        assertThat(actual, equalTo(expected));
    }
}
