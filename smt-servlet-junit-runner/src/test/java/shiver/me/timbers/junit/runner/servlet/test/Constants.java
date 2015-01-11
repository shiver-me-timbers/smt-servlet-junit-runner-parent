package shiver.me.timbers.junit.runner.servlet.test;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.ListIterable;
import shiver.me.timbers.junit.runner.servlet.Packages;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageFilterOne;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageServletOne;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageFilterTwo;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageServletTwo;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageFilterThree;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageServletThree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.WebServletBuilder.create;

public class Constants {

    public static final String INIT = "init";
    public static final String PARAM = "param";

    public static final String DESCRIPTION = "description";
    public static final String DISPLAY_NAME = "displayName";
    public static final Map<String, String> INIT_PARAMS = singletonMap(INIT, PARAM);
    public static final String SMALL_ICON = "smallIcon";
    public static final String LARGE_ICON = "largeIcon";
    public static final String VALUE = "/value";
    public static final String URL_PATTERN = "/url-pattern";
    public static final boolean ASYNC_SUPPORT = true;

    public static final String SERVLET_DETAIL_ONE_NAME = "servlet-one";
    public static final String SERVLET_DETAIL_THREE_NAME = "servlet-three";

    public static final String PACKAGE_SERVLET_DETAIL_ONE_NAME = "package-servlet-one";
    public static final String PACKAGE_SERVLET_DETAIL_TWO_NAME = "package-servlet-two";

    public static final String CONFIGURED_SERVLET_DETAIL_ONE_NAME = "configured-servlet-one";
    public static final String CONFIGURED_SERVLET_DETAIL_TWO_NAME = "configured-servlet-two";

    public static final String SERVLET_DETAIL_ONE_PATH = "/one";
    public static final String SERVLET_DETAIL_THREE_PATH = "/three";

    public static final String PACKAGE_SERVLET_DETAIL_ONE_PATH = "/package-one";
    public static final String PACKAGE_SERVLET_DETAIL_TWO_PATH = "/package-two";

    public static final String CONFIGURED_SERVLET_DETAIL_ONE_PATH = "/configured-one";
    public static final String CONFIGURED_SERVLET_DETAIL_TWO_PATH = "/configured-two";

    public static final ServletDetail SERVLET_DETAIL_ONE = new ServletDetail(ServletOne.class);
    public static final ServletDetail SERVLET_DETAIL_TWO = new ServletDetail(ServletTwo.class);
    public static final ServletDetail SERVLET_DETAIL_THREE = new ServletDetail(ServletThree.class);

    public static final ServletDetail PACKAGE_SERVLET_DETAIL_ONE = new ServletDetail(PackageServletOne.class);
    public static final ServletDetail PACKAGE_SERVLET_DETAIL_TWO = new ServletDetail(PackageServletTwo.class);
    public static final ServletDetail PACKAGE_SERVLET_DETAIL_THREE = new ServletDetail(PackageServletThree.class);

    public static final ServletDetail CONFIGURED_SERVLET_DETAIL_ONE = new ServletDetail(
            ServletOne.class,
            create().withName(CONFIGURED_SERVLET_DETAIL_ONE_NAME).withValue(CONFIGURED_SERVLET_DETAIL_ONE_PATH)
                    .build()
    );
    public static final ServletDetail CONFIGURED_SERVLET_DETAIL_TWO = new ServletDetail(
            ServletTwo.class,
            create().withName(CONFIGURED_SERVLET_DETAIL_TWO_NAME).withValue(CONFIGURED_SERVLET_DETAIL_TWO_PATH)
                    .build()
    );

    public static final FilterDetail FILTER_DETAIL_ONE = new FilterDetail(FilterOne.class);
    public static final FilterDetail FILTER_DETAIL_TWO = new FilterDetail(FilterTwo.class);
    public static final FilterDetail FILTER_DETAIL_THREE = new FilterDetail(FilterThree.class);

    public static final FilterDetail PACKAGE_FILTER_DETAIL_ONE = new FilterDetail(PackageFilterOne.class);
    public static final FilterDetail PACKAGE_FILTER_DETAIL_TWO = new FilterDetail(PackageFilterTwo.class);
    public static final FilterDetail PACKAGE_FILTER_DETAIL_THREE = new FilterDetail(PackageFilterThree.class);

    public static final String TEST_PACKAGE = "shiver.me.timbers.junit.runner.servlet.test";

    public static final String PACKAGE_ONE = TEST_PACKAGE + ".one";
    public static final String PACKAGE_TWO = TEST_PACKAGE + ".two";
    public static final String PACKAGE_THREE = TEST_PACKAGE + ".three";

    public static final String EMPTY_PACKAGE_ONE = PACKAGE_ONE + ".empty";
    public static final String EMPTY_PACKAGE_TWO = PACKAGE_TWO + ".empty";
    public static final String EMPTY_PACKAGE_THREE = PACKAGE_THREE + ".empty";

    public static Servlets mockEmptyServlets() {

        return mockListIterable(Servlets.class, new ArrayList<ServletDetail>());
    }

    public static Servlets mockAnnotatedServlets() {

        return mockServlets(SERVLET_DETAIL_ONE, SERVLET_DETAIL_TWO, SERVLET_DETAIL_THREE);
    }

    public static Servlets mockPackageServlets() {

        return mockServlets(PACKAGE_SERVLET_DETAIL_ONE, PACKAGE_SERVLET_DETAIL_TWO, PACKAGE_SERVLET_DETAIL_THREE);
    }

    public static Servlets mockConfguredServlets() {

        return mockServlets(CONFIGURED_SERVLET_DETAIL_ONE, CONFIGURED_SERVLET_DETAIL_TWO);
    }

    public static Servlets mockServlets(ServletDetail... servletDetails) {
        return mockListIterable(Servlets.class, servletDetails);
    }

    public static Servlets mockAllServlets() {

        final List<ServletDetail> list = new ArrayList<>();
        list.addAll(mockAnnotatedServlets().asList());
        list.addAll(mockPackageServlets().asList());
        list.addAll(mockConfguredServlets().asList());

        return mockListIterable(Servlets.class, list);
    }

    public static Filters mockEmptyFilters() {

        return mockListIterable(Filters.class, new ArrayList<FilterDetail>());
    }

    public static Filters mockAnnotatedFilters() {

        return mockFilters(FILTER_DETAIL_ONE, FILTER_DETAIL_TWO, FILTER_DETAIL_THREE);
    }

    public static Filters mockPackageFilters() {

        return mockFilters(PACKAGE_FILTER_DETAIL_ONE, PACKAGE_FILTER_DETAIL_TWO, PACKAGE_FILTER_DETAIL_THREE);
    }

    public static Filters mockFilters(FilterDetail... filterDetails) {
        return mockListIterable(Filters.class, filterDetails);
    }

    public static Filters mockAllFilters() {

        final List<FilterDetail> list = new ArrayList<>();
        list.addAll(mockAnnotatedFilters().asList());
        list.addAll(mockPackageFilters().asList());

        return mockListIterable(Filters.class, list);
    }

    public static Packages mockPackages() {

        return mockPackages(PACKAGE_ONE, PACKAGE_TWO, PACKAGE_THREE);
    }

    public static Packages mockNoClassPackages() {

        return mockPackages(EMPTY_PACKAGE_ONE, EMPTY_PACKAGE_TWO, EMPTY_PACKAGE_THREE);
    }

    public static Packages mockPackages(String... packageStrings) {

        return mockListIterable(Packages.class, packageStrings);
    }

    public static Packages mockEmptyPackages() {

        return mockListIterable(Packages.class, new ArrayList<String>());
    }

    @SafeVarargs
    public static <E, T extends ListIterable<T, E>> T mockListIterable(Class<T> type, E... elements) {

        final List<E> list = new ArrayList<>();

        Collections.addAll(list, elements);

        return mockListIterable(type, list);
    }

    public static <E, T extends ListIterable<T, E>> T mockListIterable(Class<T> type, List<E> list) {

        final T mock = mock(type);
        when(mock.asList()).thenReturn(list);
        when(mock.iterator()).thenAnswer(new IteratorAnswer<>(list));

        return mock;
    }

    private static class IteratorAnswer<T> implements Answer<Iterator<T>> {

        private final Iterable<T> list;

        private IteratorAnswer(Iterable<T> list) {
            this.list = list;
        }

        @Override
        public Iterator<T> answer(InvocationOnMock invocationOnMock) throws Throwable {
            return list.iterator();
        }
    }
}
