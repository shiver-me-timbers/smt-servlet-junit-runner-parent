package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Packages;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServletsPackagesAnnotationFactoryTest {

    @Test
    public void Servlets_are_returned_when_packages_are_supplied() throws Exception {

        final PackagesServletsFactory packagesServletsFactory = mock(PackagesServletsFactory.class);
        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);

        final Servlets expected = mock(Servlets.class);
        final String[] packageStrings = {};

        // Given
        when(configuration.packages()).thenReturn(packageStrings);
        when(packagesServletsFactory.create(any(Packages.class))).thenReturn(expected);

        // When
        final Servlets actual = new ServletsPackagesAnnotationFactory(packagesServletsFactory)
                .create(configuration);

        // Then
        assertEquals(expected, actual);
    }
}
