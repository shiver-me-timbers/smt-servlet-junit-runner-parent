package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyFilters;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockFilters;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;

public class FiltersAnnotationFactoryTest {

    @Test
    @SuppressWarnings("unchecked")
    public void No_filters_are_returned_if_none_are_configured() {

        // Given
        final Filters expected = mockEmptyFilters();

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.filters()).thenReturn(new Class[]{});

        // When
        final Filters filters = new FiltersAnnotationFactory().create(configuration);

        // Then
        assertThat(filters, equalAll(expected));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Filters_are_returned_if_some_are_configured() {

        // Given
        final Filters expected = mockFilters();

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);
        when(configuration.filters()).thenReturn(new Class[]{FilterOne.class, FilterTwo.class, FilterThree.class});

        // When
        final Filters filters = new FiltersAnnotationFactory().create(configuration);

        // Then
        assertThat(filters, equalAll(expected));
    }
}
