package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Packages;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PackagesAnnotationFactoryTest {

    @Test
    public void Servlets_are_returned_when_packages_are_supplied() throws Exception {

        @SuppressWarnings("unchecked")
        final PackagesFactory<Object> packagesFactory = mock(PackagesFactory.class);

        final ContainerConfiguration configuration = mock(ContainerConfiguration.class);

        final Object expected = Object.class;
        final String[] packageStrings = {};

        // Given
        when(configuration.packages()).thenReturn(packageStrings);
        when(packagesFactory.create(any(Packages.class))).thenReturn(expected);

        // When
        final Object actual = new PackagesAnnotationFactory<>(packagesFactory).create(configuration);

        // Then
        assertEquals(expected, actual);
    }
}
