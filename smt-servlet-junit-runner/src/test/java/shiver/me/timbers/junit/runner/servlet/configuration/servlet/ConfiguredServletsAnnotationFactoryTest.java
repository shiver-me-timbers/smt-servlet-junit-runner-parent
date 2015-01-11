package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.ServletConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import javax.servlet.annotation.WebServlet;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.CONFIGURED_SERVLET_DETAIL_ONE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.CONFIGURED_SERVLET_DETAIL_ONE_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.CONFIGURED_SERVLET_DETAIL_TWO_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.CONFIGURED_SERVLET_DETAIL_TWO_PATH;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockConfguredServlets;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyServlets;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;

public class ConfiguredServletsAnnotationFactoryTest {

    @Test
    @SuppressWarnings("unchecked")
    public void No_servlets_are_returned_if_none_are_configured() {

        // Given
        final Servlets expected = mockEmptyServlets();

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.servletConfigurations()).thenReturn(new ServletConfiguration[]{});

        // When
        final Servlets servlets = new ConfiguredServletsAnnotationFactory().create(configuration);

        // Then
        assertThat(servlets, equalAll(expected));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Servlets_are_returned_if_some_are_configured() {

        // Given
        final Servlets expected = mockConfguredServlets();

        @ContainerConfiguration(
                servletConfigurations = {
                        @ServletConfiguration(
                                configuration = @WebServlet(
                                        name = CONFIGURED_SERVLET_DETAIL_ONE_NAME,
                                        value = CONFIGURED_SERVLET_DETAIL_ONE_PATH
                                ),
                                servlet = ServletOne.class
                        ),
                        @ServletConfiguration(
                                configuration = @WebServlet(
                                        name = CONFIGURED_SERVLET_DETAIL_TWO_NAME,
                                        value = CONFIGURED_SERVLET_DETAIL_TWO_PATH
                                ),
                                servlet = ServletTwo.class
                        )
                }
        )
        class TestClass {
        }

        final ContainerConfiguration configuration = TestClass.class.getAnnotation(ContainerConfiguration.class);

        // When
        final Servlets actual = new ConfiguredServletsAnnotationFactory().create(configuration);

        // Then
        assertThat(actual, equalAll(expected));
    }
}
