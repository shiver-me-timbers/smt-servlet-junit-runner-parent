package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockFilters;
import static shiver.me.timbers.junit.runner.servlet.test.FiltersMatcher.equalTo;

public class SettableFiltersTest {

    @Test
    public void No_filter_classes_can_be_used_for_creation() {

        // Given
        final Filters expected = mock(Filters.class);
        when(expected.getFilters()).thenReturn(new ArrayList<FilterDetail>());

        // When
        final Filters actual = new SettableFilters();

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void An_array_of_filter_classes_can_be_used_for_creation() {

        // Given
        final Filters expected = mockFilters();

        // When
        final Filters actual = new SettableFilters(FilterOne.class, FilterTwo.class, FilterThree.class);

        // Then
        assertThat(actual, equalTo(expected));
    }
}
