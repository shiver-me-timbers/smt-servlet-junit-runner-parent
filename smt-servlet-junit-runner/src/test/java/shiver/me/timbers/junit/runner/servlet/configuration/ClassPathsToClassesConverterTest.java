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
import org.junit.matchers.JUnitMatchers;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageFilterOne;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageServletOne;
import shiver.me.timbers.junit.runner.servlet.test.one.sub.SubPackageFilterOne;
import shiver.me.timbers.junit.runner.servlet.test.one.sub.SubPackageServletOne;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.junit.runner.servlet.configuration.servlet.ClassPathConstants.JUNIT_MATCHERS_CLASS_PATHS;
import static shiver.me.timbers.junit.runner.servlet.configuration.servlet.ClassPathConstants.ONE_CLASS_PATHS;
import static shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher.equalAll;
import static shiver.me.timbers.junit.runner.servlet.test.Lists.toList;

public class ClassPathsToClassesConverterTest {

    @Test
    public void Class_paths_of_classes_are_converted_to_classes() throws Exception {

        // Given
        final List<String> classPaths = toList(ONE_CLASS_PATHS, JUNIT_MATCHERS_CLASS_PATHS);

        final List<Class> expected = Arrays.<Class>asList(
                PackageFilterOne.class,
                PackageServletOne.class,
                JUnitMatchers.class,
                SubPackageFilterOne.class,
                SubPackageServletOne.class
        );

        // When
        final List<Class> actual = new ClassPathsToClassesConverter().create(classPaths);

        // Then
        assertThat(actual, equalAll(expected));
    }

    @Test
    public void Class_paths_of_non_classes_are_not_converted_to_classes() throws Exception {

        // Given
        final List<String> classPaths = asList("not/a/class.txt", "also/not/a/class.json", "still/not/a/class");

        final List<Class> expected = emptyList();

        // When
        final List<Class> actual = new ClassPathsToClassesConverter().create(classPaths);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void No_Class_paths_are_converted_to_no_classes() throws Exception {

        // Given
        final List<String> classPaths = emptyList();

        final List<Class> expected = emptyList();

        // When
        final List<Class> actual = new ClassPathsToClassesConverter().create(classPaths);

        // Then
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_convert_class_path_to_class_that_does_not_exist() throws Exception {

        // When
        new ClassPathsToClassesConverter().create(asList("this/does/not/Exist.class"));
    }
}
