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
