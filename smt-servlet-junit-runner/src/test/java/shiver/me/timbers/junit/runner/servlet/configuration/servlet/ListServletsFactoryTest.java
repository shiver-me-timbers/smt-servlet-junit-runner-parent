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
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.mockAnnotatedServlets;
import static shiver.me.timbers.junit.runner.servlet.test.ServletConstants.mockEmptyServlets;

/**
 * @author Karl Bennett
 */
public class ListServletsFactoryTest {

    @Test
    public void An_empty_list_can_be_used_for_creation() throws Exception {

        // Given
        final Servlets expected = mockEmptyServlets();

        // When
        final Servlets actual = new ListServletsFactory().create(Collections.<Servlets>emptyList());

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void A_list_of_servletses_can_be_used_for_creation() throws Exception {

        // Given
        final Servlets one = new SettableServlets(ServletOne.class);
        final Servlets two = new SettableServlets(ServletTwo.class, ServletThree.class);
        final Servlets expected = mockAnnotatedServlets();

        // When
        final Servlets actual = new ListServletsFactory().create(asList(one, two));

        // Then
        assertThat(actual, equalAll(expected));
    }
}
