package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.WebFilterBuilder;
import shiver.me.timbers.junit.runner.servlet.test.one.PackageFilterOne;
import shiver.me.timbers.junit.runner.servlet.test.one.sub.SubPackageFilterOne;
import shiver.me.timbers.junit.runner.servlet.test.three.PackageFilterTwo;
import shiver.me.timbers.junit.runner.servlet.test.two.PackageFilterThree;

import java.util.ArrayList;
import java.util.List;

import static shiver.me.timbers.junit.runner.servlet.test.Constants.mockListIterable;

public class FilterConstants {

    public static final String FILTER_DETAIL_ONE_NAME = "filter-one";

    public static final String PACKAGE_FILTER_DETAIL_THREE_NAME = "package-filter-three";

    public static final String CONFIGURED_FILTER_DETAIL_ONE_NAME = "configured-filter-one";
    public static final String CONFIGURED_FILTER_DETAIL_THREE_NAME = "configured-filter-three";

    public static final String FILTER_DETAIL_ONE_PATH = "/filter/one";
    public static final String FILTER_DETAIL_TWO_PATH = "/filter/two";

    public static final String PACKAGE_FILTER_DETAIL_ONE_PATH = "/filter/package-one";
    public static final String PACKAGE_FILTER_DETAIL_THREE_PATH = "/filter/package-three";

    public static final String CONFIGURED_FILTER_DETAIL_ONE_PATH = "/filter/configured-one";
    public static final String CONFIGURED_FILTER_DETAIL_THREE_PATH = "/filter/configured-three";

    public static final FilterDetail FILTER_DETAIL_ONE = new FilterDetail(FilterOne.class);
    public static final FilterDetail FILTER_DETAIL_TWO = new FilterDetail(FilterTwo.class);
    public static final FilterDetail FILTER_DETAIL_THREE = new FilterDetail(FilterThree.class);

    public static final FilterDetail PACKAGE_FILTER_DETAIL_ONE = new FilterDetail(PackageFilterOne.class);
    public static final FilterDetail PACKAGE_FILTER_DETAIL_TWO = new FilterDetail(PackageFilterTwo.class);
    public static final FilterDetail PACKAGE_FILTER_DETAIL_THREE = new FilterDetail(PackageFilterThree.class);
    public static final FilterDetail SUB_PACKAGE_FILTER_DETAIL_ONE = new FilterDetail(SubPackageFilterOne.class);

    public static final FilterDetail CONFIGURED_FILTER_DETAIL_ONE = new FilterDetail(
            FilterOne.class,
            WebFilterBuilder.create()
                    .withFilterName(CONFIGURED_FILTER_DETAIL_ONE_NAME).withValue(CONFIGURED_FILTER_DETAIL_ONE_PATH)
                    .build()
    );
    public static final FilterDetail CONFIGURED_FILTER_DETAIL_THREE = new FilterDetail(
            FilterTwo.class,
            WebFilterBuilder.create()
                    .withFilterName(CONFIGURED_FILTER_DETAIL_THREE_NAME).withValue(CONFIGURED_FILTER_DETAIL_THREE_PATH)
                    .build()
    );

    public static Filters mockEmptyFilters() {

        return mockListIterable(Filters.class, new ArrayList<FilterDetail>());
    }

    public static Filters mockAnnotatedFilters() {

        return mockFilters(FILTER_DETAIL_ONE, FILTER_DETAIL_TWO, FILTER_DETAIL_THREE);
    }

    public static Filters mockPackageFilters() {

        return mockFilters(
                PACKAGE_FILTER_DETAIL_ONE,
                PACKAGE_FILTER_DETAIL_TWO,
                PACKAGE_FILTER_DETAIL_THREE,
                SUB_PACKAGE_FILTER_DETAIL_ONE
        );
    }

    public static Filters mockConfiguredFilters() {

        return mockFilters(CONFIGURED_FILTER_DETAIL_ONE, CONFIGURED_FILTER_DETAIL_THREE);
    }

    public static Filters mockFilters(FilterDetail... filterDetails) {
        return mockListIterable(Filters.class, filterDetails);
    }

    public static Filters mockAllFilters() {

        final List<FilterDetail> list = new ArrayList<>();
        list.addAll(mockAnnotatedFilters().asList());
        list.addAll(mockPackageFilters().asList());
        list.addAll(mockConfiguredFilters().asList());

        return mockListIterable(Filters.class, list);
    }
}
