package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SettableServlets implements Servlets {

    @SafeVarargs
    private static List<ServletDetail> transform(Class<? extends Servlet>... servlets) {

        final List<ServletDetail> details = new ArrayList<>(servlets.length);

        for (Class<? extends Servlet> servlet : servlets) {
            details.add(new ServletDetail(servlet));
        }

        return details;
    }

    private final List<ServletDetail> servlets;

    @SafeVarargs
    public SettableServlets(Class<? extends Servlet>... servlets) {
        this(transform(servlets));
    }

    public SettableServlets(List<ServletDetail> servlets) {
        this.servlets = servlets;
    }

    @Override
    public List<ServletDetail> asList() {
        return servlets;
    }

    @Override
    public Iterator<ServletDetail> iterator() {
        return servlets.iterator();
    }
}
