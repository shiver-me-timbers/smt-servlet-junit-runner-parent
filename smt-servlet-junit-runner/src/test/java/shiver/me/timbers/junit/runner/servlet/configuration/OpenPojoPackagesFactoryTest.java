package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.test.BaseFilter;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageFilterOne;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageServletOne;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageFilterTwo;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageFilterThree;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageServletThree;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.TEST_PACKAGE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.mockEmptyPackages;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.mockNoClassPackages;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.mockPackages;

public class OpenPojoPackagesFactoryTest {

    @Test
    public void No_packages_should_produce_an_empty_servlets() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Class<? extends Servlet>>, Object> typeConverter = mock(Factory.class);
        final Object expected = new Object();

        // Given
        when(typeConverter.create(eq(new ArrayList<Class<? extends Servlet>>()))).thenReturn(expected);

        // When
        final Object actual = new OpenPojoPackagesFactory<>(typeConverter, Servlet.class)
                .create(mockEmptyPackages());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void No_packages_should_produce_an_empty_filters() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Class<? extends Filter>>, Object> typeConverter = mock(Factory.class);
        final Object expected = new Object();

        // Given
        when(typeConverter.create(eq(new ArrayList<Class<? extends Filter>>()))).thenReturn(expected);

        // When
        final Object actual = new OpenPojoPackagesFactory<>(typeConverter, Filter.class)
                .create(mockEmptyPackages());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void Packages_that_contain_no_servlets_should_produce_an_empty_servlets() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Class<? extends Servlet>>, Object> typeConverter = mock(Factory.class);
        final Object expected = new Object();

        // Given
        when(typeConverter.create(eq(new ArrayList<Class<? extends Servlet>>()))).thenReturn(expected);

        // When
        final Object actual = new OpenPojoPackagesFactory<>(typeConverter, Servlet.class)
                .create(mockNoClassPackages());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void Packages_that_contain_no_servlets_should_produce_an_empty_filters() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Class<? extends Filter>>, Object> typeConverter = mock(Factory.class);
        final Object expected = new Object();

        // Given
        when(typeConverter.create(eq(new ArrayList<Class<? extends Filter>>()))).thenReturn(expected);

        // When
        final Object actual = new OpenPojoPackagesFactory<>(typeConverter, Filter.class)
                .create(mockNoClassPackages());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void Packages_that_contain_servlets_produce_a_populated_servlets() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Class<? extends Servlet>>, Object> typeConverter = mock(Factory.class);

        final List<Class<? extends Servlet>> types = Arrays.<Class<? extends Servlet>>asList(
                PackageServletOne.class,
                PackageServletTwo.class,
                PackageServletThree.class
        );

        final Object expected = new Object();

        // Given
        when(typeConverter.create(argThat(equalAll(types)))).thenReturn(expected);

        // When
        final Object actual = new OpenPojoPackagesFactory<>(typeConverter, Servlet.class).create(mockPackages());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void Packages_that_contain_filters_produce_a_populated_filters() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Class<? extends Filter>>, Object> typeConverter = mock(Factory.class);

        final List<Class<? extends Filter>> types = Arrays.<Class<? extends Filter>>asList(
                PackageFilterOne.class,
                PackageFilterTwo.class,
                PackageFilterThree.class
        );

        final Object expected = new Object();

        // Given
        when(typeConverter.create(argThat(equalAll(types)))).thenReturn(expected);

        // When
        final Object actual = new OpenPojoPackagesFactory<>(typeConverter, Filter.class).create(mockPackages());

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void Packages_that_contain_packages_with_servlets_produce_a_populated_servlets() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Class<? extends Servlet>>, Object> typeConverter = mock(Factory.class);

        final List<Class<? extends Servlet>> types = Arrays.<Class<? extends Servlet>>asList(
                ServletOne.class,
                ServletTwo.class,
                ServletThree.class,
                PackageServletOne.class,
                PackageServletTwo.class,
                PackageServletThree.class
        );

        final Object expected = new Object();

        // Given
        when(typeConverter.create(argThat(equalAll(types)))).thenReturn(expected);

        // When
        final Object actual = new OpenPojoPackagesFactory<>(typeConverter, Servlet.class)
                .create(mockPackages(TEST_PACKAGE));

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void Packages_that_contain_packages_with_filters_produce_a_populated_filters() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Class<? extends Filter>>, Object> typeConverter = mock(Factory.class);

        final List<Class<? extends Filter>> types = Arrays.<Class<? extends Filter>>asList(
                BaseFilter.class,
                FilterOne.class,
                FilterTwo.class,
                FilterThree.class,
                PackageFilterOne.class,
                PackageFilterTwo.class,
                PackageFilterThree.class
        );

        final Object expected = new Object();

        // Given
        when(typeConverter.create(argThat(equalAll(types)))).thenReturn(expected);

        // When
        final Object actual = new OpenPojoPackagesFactory<>(typeConverter, Filter.class)
                .create(mockPackages(TEST_PACKAGE));

        // Then
        assertEquals(expected, actual);
    }
}
