package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyServlets;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockServlets;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;

public class AnnotationServletsFactoryTest {

    @Test
    public void No_servlets_are_returned_if_no_configuration_supplied() {

        // Given
        final Servlets expected = mockEmptyServlets();

        class TestClass {
        }

        // When
        final Servlets servlets = new AnnotationServletsFactory().create(new TestClass());

        // Then
        assertThat(servlets, equalTo(expected));
    }

    @Test
    public void No_servlets_are_returned_if_none_are_configured() {

        // Given
        final Servlets expected = mockEmptyServlets();

        @ContainerConfiguration
        class TestClass {
        }

        // When
        final Servlets servlets = new AnnotationServletsFactory().create(new TestClass());

        // Then
        assertThat(servlets, equalTo(expected));
    }

    @Test
    public void Servlets_are_returned_if_some_are_configured() {

        // Given
        final Servlets expected = mockServlets();

        @ContainerConfiguration(servlets = {ServletOne.class, ServletTwo.class, ServletThree.class})
        class TestClass {
        }

        // When
        final Servlets servlets = new AnnotationServletsFactory().create(new TestClass());

        // Then
        assertThat(servlets, equalTo(expected));
    }
}