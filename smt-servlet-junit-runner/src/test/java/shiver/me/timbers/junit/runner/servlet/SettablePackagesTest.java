package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

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
public class SettablePackagesTest {

    @Test
    public void No_package_classes_can_be_used_for_creation() {

        // Given
        final Packages expected = mockEmptyPackages();

        // When
        final Packages actual = new SettablePackages();

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void An_array_of_package_classes_can_be_used_for_creation() {

        // Given
        final Packages expected = mockPackages();

        // When
        final Packages actual = new SettablePackages(PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE);

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void An_invalid_package_cannot_be_used_for_creation() {

        // When
        new SettablePackages(PACKAGE_ONE, "this.does.not.exist", PACKAGE_THREE);
    }
}
