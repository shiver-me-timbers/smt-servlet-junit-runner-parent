package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Filters;

import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyFilters;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;

public class FiltersEmptyFactoryTest {

    @Test
    public void No_filters_should_be_created() {

        // Given
        final Filters expected = mockEmptyFilters();

        // When
        final Filters filters = new FiltersEmptyFactory().create();

        // Then
        assertThat(filters, equalAll(expected));
    }
}
