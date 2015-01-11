package shiver.me.timbers.junit.runner.servlet.test.two;

import shiver.me.timbers.junit.runner.servlet.test.BaseFilter;

import javax.servlet.annotation.WebFilter;

import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.PACKAGE_FILTER_DETAIL_THREE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.PACKAGE_FILTER_DETAIL_THREE_PATH;

@WebFilter(filterName = PACKAGE_FILTER_DETAIL_THREE_NAME, value = PACKAGE_FILTER_DETAIL_THREE_PATH)
public class PackageFilterThree extends BaseFilter {
}
