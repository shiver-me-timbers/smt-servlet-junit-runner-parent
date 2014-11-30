package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Servlet;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SettableServlets implements Servlets {

    private final List<Class<? extends Servlet>> servlets;

    public SettableServlets(List<Class<? extends Servlet>> servlets) {
        this.servlets = servlets;
    }

    @Override
    public List<Class<? extends Servlet>> getServlets() {
        return servlets;
    }
}
