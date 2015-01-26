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
