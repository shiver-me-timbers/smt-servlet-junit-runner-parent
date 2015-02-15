/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
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

    @Test
    public void Packages_can_be_toStringed() {

        // Given
        final List<String> list = asList(PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE);
        final Packages packages = new SettablePackages(list);
        final String expected = format("SettablePackages { packages = '%s' }", list);

        // When
        final String actual = packages.toString();

        // Then
        assertEquals(expected, actual);
    }
}
