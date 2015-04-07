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

package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.junit.matchers.JUnitMatchers;
import shiver.me.timbers.junit.runner.servlet.test.BaseFilter;
import shiver.me.timbers.junit.runner.servlet.test.Constants;
import shiver.me.timbers.junit.runner.servlet.test.ContainsAllMatcher;
import shiver.me.timbers.junit.runner.servlet.test.EqualAllMatcher;
import shiver.me.timbers.junit.runner.servlet.test.FilterConstants;
import shiver.me.timbers.junit.runner.servlet.test.FilterOne;
import shiver.me.timbers.junit.runner.servlet.test.FilterThree;
import shiver.me.timbers.junit.runner.servlet.test.FilterTwo;
import shiver.me.timbers.junit.runner.servlet.test.Lists;
import shiver.me.timbers.junit.runner.servlet.test.PackageConstants;
import shiver.me.timbers.junit.runner.servlet.test.ServletConstants;
import shiver.me.timbers.junit.runner.servlet.test.ServletOne;
import shiver.me.timbers.junit.runner.servlet.test.ServletThree;
import shiver.me.timbers.junit.runner.servlet.test.ServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.TestAnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.servlet.test.TestContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.test.TestServletContainer;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageFilterOne;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageServletOne;
import shiver.me.timbers.junit.runner.servlet.test.one.sub.SubPackageFilterOne;
import shiver.me.timbers.junit.runner.servlet.test.one.sub.SubPackageServletOne;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageFilterTwo;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageFilterThree;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageServletThree;

import java.util.List;

import static shiver.me.timbers.junit.runner.servlet.test.Lists.toList;

public class ClassPathConstants {

    public static final String BASE_FILTER_CLASS_PATH = convertToPath(BaseFilter.class);
    public static final String CONSTANTS_CLASS_PATH = convertToPath(Constants.class);
    public static final String CONSTANTS_INNER_CLASS_CLASS_PATH = convertToPath(Constants.class, "Constants$1.class");
    public static final String CONSTANTS_ITERATOR_ANSWER_CLASS_PATH = convertToPath(Constants.class,
            "Constants$IteratorAnswer.class");
    public static final String CONTAINS_ALL_MATCHER_CLASS_PATH = convertToPath(ContainsAllMatcher.class);
    public static final String EQUAL_ALL_MATCHER_CLASS_PATH = convertToPath(EqualAllMatcher.class);
    public static final String FILTER_CONSTANTS_CLASS_PATH = convertToPath(FilterConstants.class);
    public static final String FILTER_ONE_CLASS_PATH = convertToPath(FilterOne.class);
    public static final String FILTER_TWO_CLASS_PATH = convertToPath(FilterTwo.class);
    public static final String FILTER_THREE_CLASS_PATH = convertToPath(FilterThree.class);
    public static final String LISTS_CLASS_PATH = convertToPath(Lists.class);
    public static final String PACKAGE_CONSTANTS_CLASS_PATH = convertToPath(PackageConstants.class);
    public static final String SERVLET_CONSTANTS_CLASS_PATH = convertToPath(ServletConstants.class);
    public static final String SERVLET_ONE_CLASS_PATH = convertToPath(ServletOne.class);
    public static final String SERVLET_TWO_CLASS_PATH = convertToPath(ServletTwo.class);
    public static final String SERVLET_THREE_CLASS_PATH = convertToPath(ServletThree.class);
    public static final String TEST_ANNOTATIONS_SERVLET_JUNIT_RUNNER_CLASS_PATH = convertToPath(
            TestAnnotationServletJUnitRunner.class);
    public static final String TEST_ANNOTATIONS_SERVLET_JUNIT_RUNNER_INNER_CLASS_CLASS_PATH = convertToPath(
            TestAnnotationServletJUnitRunner.class, "TestAnnotationServletJUnitRunner$1.class");
    public static final String TEST_CONTAINER_CONFIGURATION_CLASS_PATH = convertToPath(
            TestContainerConfiguration.class);
    public static final String TEST_SERVLET_CONTAINER_CLASS_PATH = convertToPath(TestServletContainer.class);

    public static final String PACKAGE_FILTER_ONE_CLASS_PATH = convertToPath(PackageFilterOne.class);
    public static final String PACKAGE_SERVLET_ONE_CLASS_PATH = convertToPath(PackageServletOne.class);
    public static final String PLACE_HOLDER_ONE_CLASS_PATH = convertToPath(PackageServletOne.class,
            "empty/place-holder");
    public static final String SUB_PACKAGE_FILTER_ONE_CLASS_PATH = convertToPath(SubPackageFilterOne.class);
    public static final String SUB_PACKAGE_SERVLET_ONE_CLASS_PATH = convertToPath(SubPackageServletOne.class);

    public static final String PACKAGE_FILTER_TWO_CLASS_PATH = convertToPath(PackageFilterTwo.class);
    public static final String PACKAGE_SERVLET_TWO_CLASS_PATH = convertToPath(PackageServletTwo.class);
    public static final String PLACE_HOLDER_TWO_CLASS_PATH = convertToPath(PackageServletTwo.class,
            "empty/place-holder");

    public static final String PACKAGE_FILTER_THREE_CLASS_PATH = convertToPath(PackageFilterThree.class);
    public static final String PACKAGE_SERVLET_THREE_CLASS_PATH = convertToPath(PackageServletThree.class);
    public static final String PLACE_HOLDER_THREE_CLASS_PATH = convertToPath(PackageServletThree.class,
            "empty/place-holder");

    public static final String JUNIT_MATCHERS_CLASS_PATH = convertToPath(JUnitMatchers.class);

    public static final List<String> ONE_CLASS_PATHS = toList(
            PACKAGE_FILTER_ONE_CLASS_PATH,
            PACKAGE_SERVLET_ONE_CLASS_PATH,
            PLACE_HOLDER_ONE_CLASS_PATH,
            SUB_PACKAGE_FILTER_ONE_CLASS_PATH,
            SUB_PACKAGE_SERVLET_ONE_CLASS_PATH
    );

    public static final List<String> TWO_CLASS_PATHS = toList(
            PACKAGE_FILTER_TWO_CLASS_PATH,
            PACKAGE_SERVLET_TWO_CLASS_PATH,
            PLACE_HOLDER_TWO_CLASS_PATH
    );

    public static final List<String> THREE_CLASS_PATHS = toList(
            PACKAGE_FILTER_THREE_CLASS_PATH,
            PACKAGE_SERVLET_THREE_CLASS_PATH,
            PLACE_HOLDER_THREE_CLASS_PATH
    );

    public static final List<String> TEST_CLASS_PATHS = toList(
            ONE_CLASS_PATHS,
            TWO_CLASS_PATHS,
            THREE_CLASS_PATHS,
            toList(
                    BASE_FILTER_CLASS_PATH,
                    CONSTANTS_CLASS_PATH,
                    CONSTANTS_INNER_CLASS_CLASS_PATH,
                    CONSTANTS_ITERATOR_ANSWER_CLASS_PATH,
                    CONTAINS_ALL_MATCHER_CLASS_PATH,
                    EQUAL_ALL_MATCHER_CLASS_PATH,
                    FILTER_CONSTANTS_CLASS_PATH,
                    FILTER_ONE_CLASS_PATH,
                    FILTER_TWO_CLASS_PATH,
                    FILTER_THREE_CLASS_PATH,
                    LISTS_CLASS_PATH,
                    PACKAGE_CONSTANTS_CLASS_PATH,
                    SERVLET_CONSTANTS_CLASS_PATH,
                    SERVLET_ONE_CLASS_PATH,
                    SERVLET_TWO_CLASS_PATH,
                    SERVLET_THREE_CLASS_PATH,
                    TEST_ANNOTATIONS_SERVLET_JUNIT_RUNNER_CLASS_PATH,
                    TEST_ANNOTATIONS_SERVLET_JUNIT_RUNNER_INNER_CLASS_CLASS_PATH,
                    TEST_CONTAINER_CONFIGURATION_CLASS_PATH,
                    TEST_SERVLET_CONTAINER_CLASS_PATH
            )
    );

    public static final List<String> JUNIT_MATCHERS_CLASS_PATHS = toList(
            JUNIT_MATCHERS_CLASS_PATH
    );

    public static String convertToPath(Class type) {
        return convertToPath(type, type.getSimpleName() + ".class");
    }

    public static String convertToPath(Class type, String fileName) {
        final String name = type.getName();

        final String pkg = name.replace(type.getSimpleName(), "");

        final String path = pkg.replaceAll("\\.", "/");

        return path + fileName;
    }
}
