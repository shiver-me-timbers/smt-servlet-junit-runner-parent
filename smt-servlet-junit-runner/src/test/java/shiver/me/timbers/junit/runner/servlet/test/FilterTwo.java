package shiver.me.timbers.junit.runner.servlet.test;

import javax.servlet.annotation.WebFilter;

import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.FILTER_DETAIL_TWO_PATH;

@WebFilter(FILTER_DETAIL_TWO_PATH)
public class FilterTwo extends BaseFilter {
}
