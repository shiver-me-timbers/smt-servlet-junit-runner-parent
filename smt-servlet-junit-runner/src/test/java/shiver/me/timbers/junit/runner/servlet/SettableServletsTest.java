package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.test.Constants;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;

public class SettableServletsTest {

    @Test
    public void An_array_of_servlet_classes_can_be_used_for_creation() {

        // Given
        final Servlets expected = Constants.mockAnnotatedServlets();

        // When
        final Servlets actual = new SettableServlets(ServletOne.class, ServletTwo.class, ServletThree.class);

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void A_list_of_servlet_details_can_be_used_for_creation() {

        // Given
        final Servlets expected = Constants.mockAnnotatedServlets();

        // When
        final Servlets actual = new SettableServlets(asList(new ServletDetail(ServletOne.class),
                new ServletDetail(ServletTwo.class), new ServletDetail(ServletThree.class)));

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void An_array_of_servlets_can_be_used_for_creation() {

        // Given
        final Servlets one = new SettableServlets(ServletOne.class);
        final Servlets two = new SettableServlets(ServletTwo.class, ServletThree.class);
        final Servlets expected = Constants.mockAnnotatedServlets();

        // When
        final Servlets actual = new SettableServlets(one, two);

        // Then
        assertThat(actual, equalAll(expected));
    }
}
