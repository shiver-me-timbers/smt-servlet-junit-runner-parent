package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SettableFilters implements Filters {

    @SafeVarargs
    private static List<FilterDetails> transform(Class<? extends Filter>... servlets) {

        final List<FilterDetails> details = new ArrayList<>(servlets.length);

        for (Class<? extends Filter> servlet : servlets) {
            details.add(new FilterDetails(servlet));
        }

        return details;
    }

    private final List<FilterDetails> servlets;

    @SafeVarargs
    public SettableFilters(Class<? extends Filter>... servlets) {
        this(transform(servlets));
    }

    public SettableFilters(List<FilterDetails> servlets) {
        this.servlets = servlets;
    }

    @Override
    public List<FilterDetails> getFilters() {
        return servlets;
    }
}
