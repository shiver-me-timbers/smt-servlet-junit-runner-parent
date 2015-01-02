package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Servlets;

import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyServlets;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;

public class ServletsEmptyFactoryTest {

    @Test
    public void No_servlets_should_be_created() {

        // Given
        final Servlets expected = mockEmptyServlets();

        // When
        final Servlets actual = new ServletsEmptyFactory().create();

        // Then
        assertThat(actual, equalTo(expected));
    }
}
