package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PACKAGE_ONE;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PACKAGE_THREE;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PACKAGE_TWO;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockEmptyPackages;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockPackages;
import static shiver.me.timbers.junit.runner.servlet.test.PackagessMatcher.equalTo;

/**
 * @author Karl Bennett
 */
public class AnnotationPackagesFactoryTest {

    @Test
    public void No_packages_are_returned_if_none_are_configured() {

        // Given
        final Packages expected = mockEmptyPackages();

        class TestClass {
        }

        // When
        final Packages packages = new AnnotationPackagesFactory().create(new TestClass());

        // Then
        assertThat(packages, equalTo(expected));
    }

    @Test
    public void Packages_are_returned_if_some_are_configured() {

        // Given
        final Packages expected = mockPackages();

        @ContainerConfiguration(packages = {PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE})
        class TestClass {
        }

        // When
        final Packages packages = new AnnotationPackagesFactory().create(new TestClass());

        // Then
        assertThat(packages, equalTo(expected));
    }
}
