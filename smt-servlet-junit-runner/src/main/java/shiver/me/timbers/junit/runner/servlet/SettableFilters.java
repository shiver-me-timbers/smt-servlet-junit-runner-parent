package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Filter;
import java.util.ArrayList;
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

    private final List<FilterDetail> servlets;

    @SafeVarargs
    public SettableFilters(Class<? extends Filter>... servlets) {
        this(transform(servlets));
    }

    public SettableFilters(List<FilterDetail> servlets) {
        this.servlets = servlets;
    }

    @Override
    public List<FilterDetail> getFilters() {
        return servlets;
    }
}
