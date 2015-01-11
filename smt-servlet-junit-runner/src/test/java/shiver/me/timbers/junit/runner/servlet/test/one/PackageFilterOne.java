package shiver.me.timbers.junit.runner.servlet.test.one;

import shiver.me.timbers.junit.runner.servlet.test.BaseFilter;

import javax.servlet.annotation.WebFilter;

import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.PACKAGE_FILTER_DETAIL_ONE_PATH;

@WebFilter(PACKAGE_FILTER_DETAIL_ONE_PATH)
public class PackageFilterOne extends BaseFilter {
}
