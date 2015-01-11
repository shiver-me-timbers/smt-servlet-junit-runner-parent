package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.test.Constants;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;

public class SettableFiltersTest {

    @Test
    public void An_array_of_filter_classes_can_be_used_for_creation() {

        // Given
        final Filters expected = Constants.mockAnnotatedFilters();

        // When
        final Filters actual = new SettableFilters(FilterOne.class, FilterTwo.class, FilterThree.class);

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void A_list_of_filter_details_can_be_used_for_creation() {

        // Given
        final Filters expected = Constants.mockAnnotatedFilters();

        // When
        final Filters actual = new SettableFilters(asList(new FilterDetail(FilterOne.class),
                new FilterDetail(FilterTwo.class), new FilterDetail(FilterThree.class)));

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void An_array_of_filters_can_be_used_for_creation() {

        // Given
        final Filters one = new SettableFilters(FilterOne.class);
        final Filters two = new SettableFilters(FilterTwo.class, FilterThree.class);
        final Filters expected = Constants.mockAnnotatedFilters();

        // When
        final Filters actual = new SettableFilters(one, two);

        // Then
        assertThat(actual, equalAll(expected));
    }
}
