package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyServlets;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockServlets;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;

/**
 * @author Karl Bennett
 */
public class ServletsListFactoryTest {

    @Test
    public void An_empty_list_can_be_used_for_creation() throws Exception {

        // Given
        final Servlets expected = mockEmptyServlets();

        // When
        final Servlets actual = new ServletsListFactory().create(Collections.<Servlets>emptyList());

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void A_list_of_servletses_can_be_used_for_creation() throws Exception {

        // Given
        final Servlets one = new SettableServlets(ServletOne.class);
        final Servlets two = new SettableServlets(ServletTwo.class, ServletThree.class);
        final Servlets expected = mockServlets();

        // When
        final Servlets actual = new ServletsListFactory().create(asList(one, two));

        // Then
        assertThat(actual, equalAll(expected));
    }
}
