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

package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.Packages;

import java.util.ArrayList;

import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockListIterable;

public class PackageConstants {

    public static final String TEST_PACKAGE = "shiver.me.timbers.junit.runner.servlet.test";

    public static final String PACKAGE_ONE = TEST_PACKAGE + ".one";
    public static final String PACKAGE_TWO = TEST_PACKAGE + ".two";
    public static final String PACKAGE_THREE = TEST_PACKAGE + ".three";

    public static final String JUNIT_MATCHES_PACKAGE = "org.junit.matchers";

    public static Packages mockEmptyPackages() {

        return mockListIterable(Packages.class, new ArrayList<String>());
    }

    public static Packages mockPackages() {

        return mockPackages(PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE);
    }

    public static Packages mockPackages(String... packageStrings) {

        return mockListIterable(Packages.class, packageStrings);
    }
}
