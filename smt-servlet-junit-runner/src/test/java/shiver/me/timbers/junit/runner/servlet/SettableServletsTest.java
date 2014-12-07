package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockServlets;
import static shiver.me.timbers.junit.runner.servlet.test.ServletsMatcher.equalTo;

public class SettableServletsTest {

    @Test
    public void No_servlet_classes_can_be_used_for_creation() {

        // Given
        final Servlets expected = mock(Servlets.class);
        when(expected.getServlets()).thenReturn(new ArrayList<ServletDetails>());

        // When
        final Servlets actual = new SettableServlets();

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void An_array_of_servlet_classes_can_be_used_for_creation() {

        // Given
        final Servlets expected = mockServlets();

        // When
        final Servlets actual = new SettableServlets(ServletOne.class, ServletTwo.class, ServletThree.class);

        // Then
        assertThat(actual, equalTo(expected));
    }
}
