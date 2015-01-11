package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.annotation.WebFilter;

import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.FILTER_DETAIL_ONE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.FILTER_DETAIL_ONE_PATH;

@WebFilter(filterName = FILTER_DETAIL_ONE_NAME, value = FILTER_DETAIL_ONE_PATH)
public class FilterOne extends BaseFilter {
}
