package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class SettableFilters implements Filters {

    @SafeVarargs
    private static List<FilterDetail> transform(Class<? extends Filter>... filters) {

        final List<FilterDetail> details = new ArrayList<>(filters.length);

        for (Class<? extends Filter> filter : filters) {
            details.add(new FilterDetail(filter));
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

    public SettableFilters(Filters... filterses) {
        this.filters = new ArrayList<>();

        for (Filters filters : filterses) {
            add(filters);
        }
    }

    @Override
    public List<FilterDetail> asList() {
        return filters;
    }

    @Override
    public void add(Filters filters) {
        this.filters.addAll(filters.asList());
    }

    @Override
    public Iterator<FilterDetail> iterator() {
        return filters.iterator();
    }

    @Override
    public String toString() {
        return format("%s { filters = '%s' }", getClass().getSimpleName(), filters);
    }
}
