package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;

import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyFilters;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockFilters;
import static shiver.me.timbers.junit.runner.servlet.test.FiltersMatcher.equalTo;

public class AnnotationFiltersFactoryTest {

    @Test
    public void No_filters_are_returned_if_none_are_configured() {

        // Given
        final Filters expected = mockEmptyFilters();

        class TestClass {
        }

        // When
        final Filters filters = new AnnotationFiltersFactory().create(new TestClass());

        // Then
        assertThat(filters, equalTo(expected));
    }

    @Test
    public void Filters_are_returned_if_some_are_configured() {

        // Given
        final Filters expected = mockFilters();

        @ContainerConfiguration(filters = {FilterOne.class, FilterTwo.class, FilterThree.class})
        class TestClass {
        }

        // When
        final Filters filters = new AnnotationFiltersFactory().create(new TestClass());

        // Then
        assertThat(filters, equalTo(expected));
    }
}
