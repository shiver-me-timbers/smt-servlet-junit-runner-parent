package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SettableFilters implements Filters {

    @SafeVarargs
    private static List<FilterDetail> transform(Class<? extends Filter>... servlets) {

        final List<FilterDetail> details = new ArrayList<>(servlets.length);

        for (Class<? extends Filter> servlet : servlets) {
            details.add(new FilterDetail(servlet));
        }

        return details;
    }

    private final List<FilterDetail> filters;

    @SafeVarargs
    public SettableFilters(Class<? extends Filter>... filters) {
        this(transform(filters));
    }

    public SettableFilters(List<FilterDetail> filters) {
        this.filters = filters;
    }

    @Override
    public List<FilterDetail> asList() {
        return filters;
    }

    @Override
    public Iterator<FilterDetail> iterator() {
        return filters.iterator();
    }
}
