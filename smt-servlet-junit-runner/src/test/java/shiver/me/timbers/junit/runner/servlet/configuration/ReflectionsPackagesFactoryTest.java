package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageServletOne;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageServletThree;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyPackages;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockNoServletPackages;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockPackages;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;

public class ReflectionsPackagesFactoryTest {

    @Test
    public void No_packages_should_produce_an_empty_servlets() throws Exception {

        @SuppressWarnings("unchecked")
        final Factory<List<Class<? extends Servlet>>, Object> typeConverter = mock(Factory.class);
        final Object expected = new Object();

        // Given
        when(typeConverter.create(eq(new ArrayList<Class<? extends Servlet>>()))).thenReturn(expected);

        // When
        final Object actual = new ReflectionsPackagesFactory<>(typeConverter).create(mockEmptyPackages());

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
        final Object actual = new ReflectionsPackagesFactory<>(typeConverter).create(mockNoServletPackages());

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
        final Object actual = new ReflectionsPackagesFactory<>(typeConverter).create(mockPackages());

        // Then
        assertEquals(expected, actual);
    }
}
