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

package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.Packages;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.configuration.servlet.ClassPathConstants.JUNIT_MATCHERS_CLASS_PATHS;
import static shiver.me.timbers.junit.runner.servlet.configuration.servlet.ClassPathConstants.ONE_CLASS_PATHS;
import static shiver.me.timbers.junit.runner.servlet.configuration.servlet.ClassPathConstants.TEST_CLASS_PATHS;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.Lists.toList;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.JUNIT_MATCHES_PACKAGE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.PACKAGE_ONE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.TEST_PACKAGE;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.mockEmptyPackages;
import static shiver.me.timbers.junit.runner.servlet.test.PackageConstants.mockPackages;

public class ResourceClassPathsFactoryTest {

    @Test
    public void If_no_packages_are_supplied_no_class_paths_are_returned() throws Exception {

        // Given
        final Packages packages = mockEmptyPackages();

        final List<String> expected = emptyList();

        // When
        final List<String> actual = new ResourceClassPathsFactory().create(packages);

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void It_is_possible_to_get_a_list_of_class_paths_from_some_packages() throws Exception {

        // Given
        final Packages packages = mockPackages(PACKAGE_ONE, JUNIT_MATCHES_PACKAGE);

        final List<String> expected = toList(ONE_CLASS_PATHS, JUNIT_MATCHERS_CLASS_PATHS);

        // When
        final List<String> actual = new ResourceClassPathsFactory().create(packages);

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void It_is_possible_to_get_a_recursive_list_of_class_paths_from_a_package() throws Exception {

        // Given
        final Packages packages = mockPackages(TEST_PACKAGE);

        final List<String> expected = TEST_CLASS_PATHS;

        // When
        final List<String> actual = new ResourceClassPathsFactory().create(packages);

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void It_is_not_possible_to_get_a_list_of_class_paths_from_a_file_name() throws Exception {

        // Given
        final String fileName = "shiver.me.timbers.junit.runner.servlet.test.one.empty.place-holder";
        final Packages packages = mockPackages(fileName);

        // When
        new ResourceClassPathsFactory().create(packages);
    }

    @Test(expected = IllegalArgumentException.class)
    public void It_is_not_possible_to_get_a_list_of_class_paths_from_a_file_name_in_a_jar() throws Exception {

        // Given
        final Packages packages = mockPackages("NOTICE");

        // When
        new ResourceClassPathsFactory().create(packages);
    }

    @Test(expected = IllegalArgumentException.class)
    public void It_is_not_possible_to_get_a_list_of_class_paths_from_an_invalid_package() throws Exception {

        // Given
        final Packages packages = mockPackages("invalid.package");

        // When
        new ResourceClassPathsFactory().create(packages);
    }

    @Test(expected = IllegalStateException.class)
    public void Make_jar_file_creation_fail_for_100_percent_coverage() throws Exception {

        ResourceClassPathsFactory.filesInJarPath("invalid/path/to.jar");
    }
}
