package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Servlets;

import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyPackages;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyServlets;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockNoServletPackages;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockPackageServlets;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockPackages;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;

public class ReflectionsPackagesServletsFactoryTest {

    @Test
    public void No_packages_should_produce_an_empty_servlets() throws Exception {

        // Given
        final Servlets expected = mockEmptyServlets();

        // When
        final Servlets actual = new ReflectionsPackagesServletsFactory().create(mockEmptyPackages());

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void Packages_that_contain_no_servlets_should_produce_an_empty_servlets() throws Exception {

        // Given
        final Servlets expected = mockEmptyServlets();

        // When
        final Servlets actual = new ReflectionsPackagesServletsFactory().create(mockNoServletPackages());

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void Packages_that_contain_servlets_produce_a_populated_servlets() throws Exception {

        // Given
        final Servlets expected = mockPackageServlets();

        // When
        final Servlets actual = new ReflectionsPackagesServletsFactory().create(mockPackages());

        // Then
        assertThat(actual, equalAll(expected));
    }
}
