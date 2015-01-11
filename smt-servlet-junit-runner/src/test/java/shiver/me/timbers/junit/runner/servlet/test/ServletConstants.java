package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.WebServletBuilder;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageServletOne;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageServletThree;

import java.util.ArrayList;
import java.util.List;

import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockListIterable;

public class ServletConstants {

    public static final String SERVLET_DETAIL_ONE_NAME = "servlet-one";
    public static final String SERVLET_DETAIL_THREE_NAME = "servlet-three";

    public static final String PACKAGE_SERVLET_DETAIL_ONE_NAME = "package-servlet-one";
    public static final String PACKAGE_SERVLET_DETAIL_TWO_NAME = "package-servlet-two";

    public static final String CONFIGURED_SERVLET_DETAIL_ONE_NAME = "configured-servlet-one";
    public static final String CONFIGURED_SERVLET_DETAIL_TWO_NAME = "configured-servlet-two";

    public static final String SERVLET_DETAIL_ONE_PATH = "/servlet/one";
    public static final String SERVLET_DETAIL_THREE_PATH = "/servlet/three";

    public static final String PACKAGE_SERVLET_DETAIL_ONE_PATH = "/servlet/package-one";
    public static final String PACKAGE_SERVLET_DETAIL_TWO_PATH = "/servlet/package-two";

    public static final String CONFIGURED_SERVLET_DETAIL_ONE_PATH = "/servlet/configured-one";
    public static final String CONFIGURED_SERVLET_DETAIL_TWO_PATH = "/servlet/configured-two";

    public static final ServletDetail SERVLET_DETAIL_ONE = new ServletDetail(ServletOne.class);
    public static final ServletDetail SERVLET_DETAIL_TWO = new ServletDetail(ServletTwo.class);
    public static final ServletDetail SERVLET_DETAIL_THREE = new ServletDetail(ServletThree.class);

    public static final ServletDetail PACKAGE_SERVLET_DETAIL_ONE = new ServletDetail(PackageServletOne.class);
    public static final ServletDetail PACKAGE_SERVLET_DETAIL_TWO = new ServletDetail(PackageServletTwo.class);
    public static final ServletDetail PACKAGE_SERVLET_DETAIL_THREE = new ServletDetail(PackageServletThree.class);

    public static final ServletDetail CONFIGURED_SERVLET_DETAIL_ONE = new ServletDetail(
            ServletOne.class,
            WebServletBuilder.create()
                    .withName(CONFIGURED_SERVLET_DETAIL_ONE_NAME).withValue(CONFIGURED_SERVLET_DETAIL_ONE_PATH)
                    .build()
    );
    public static final ServletDetail CONFIGURED_SERVLET_DETAIL_TWO = new ServletDetail(
            ServletTwo.class,
            WebServletBuilder.create()
                    .withName(CONFIGURED_SERVLET_DETAIL_TWO_NAME).withValue(CONFIGURED_SERVLET_DETAIL_TWO_PATH)
                    .build()
    );

    public static Servlets mockEmptyServlets() {

        return mockListIterable(Servlets.class, new ArrayList<ServletDetail>());
    }

    public static Servlets mockAnnotatedServlets() {

        return mockServlets(SERVLET_DETAIL_ONE, SERVLET_DETAIL_TWO, SERVLET_DETAIL_THREE);
    }

    public static Servlets mockPackageServlets() {

        return mockServlets(PACKAGE_SERVLET_DETAIL_ONE, PACKAGE_SERVLET_DETAIL_TWO, PACKAGE_SERVLET_DETAIL_THREE);
    }

    public static Servlets mockConfiguredServlets() {

        return mockServlets(CONFIGURED_SERVLET_DETAIL_ONE, CONFIGURED_SERVLET_DETAIL_TWO);
    }

    public static Servlets mockServlets(ServletDetail... servletDetails) {
        return mockListIterable(Servlets.class, servletDetails);
    }

    public static Servlets mockAllServlets() {

        final List<ServletDetail> list = new ArrayList<>();
        list.addAll(mockAnnotatedServlets().asList());
        list.addAll(mockPackageServlets().asList());
        list.addAll(mockConfiguredServlets().asList());

        return mockListIterable(Servlets.class, list);
    }
}
