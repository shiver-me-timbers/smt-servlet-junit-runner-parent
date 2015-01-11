package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_ONE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_THREE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_TWO;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.mockPackages;

public class SettablePackagesTest {

    @Test
    public void An_array_of_package_strings_can_be_used_for_creation() {

        // Given
        final Packages expected = mockPackages();

        // When
        final Packages actual = new SettablePackages(PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE);

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void A_list_of_servlet_details_can_be_used_for_creation() {

        // Given
        final Packages expected = mockPackages();

        // When
        final Packages actual = new SettablePackages(asList(PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE));

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void An_array_of_Packages_can_be_used_for_creation() {

        // Given
        final Packages one = new SettablePackages(PACKAGE_ONE);
        final Packages two = new SettablePackages(PACKAGE_TWO, PACKAGE_THREE);
        final Packages expected = mockPackages();

        // When
        final Packages actual = new SettablePackages(one, two);

        // Then
        assertThat(actual, equalAll(expected));
    }
}
