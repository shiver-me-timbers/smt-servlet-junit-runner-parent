package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SettableServlets implements Servlets {

    @SafeVarargs
    private static List<ServletDetails> transform(Class<? extends Servlet>... servlets) {

        final List<ServletDetails> details = new ArrayList<>(servlets.length);

        for (Class<? extends Servlet> servlet : servlets) {
            details.add(new ServletDetails(servlet));
        }

        return details;
    }

    private final List<ServletDetails> servlets;

    @SafeVarargs
    public SettableServlets(Class<? extends Servlet>... servlets) {
        this(transform(servlets));
    }

    public SettableServlets(List<ServletDetails> servlets) {
        this.servlets = servlets;
    }

    @Override
    public List<ServletDetails> getServlets() {
        return servlets;
    }
}
