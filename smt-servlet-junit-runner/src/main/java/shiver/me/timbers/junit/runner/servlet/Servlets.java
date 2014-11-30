package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.Servlet;
import java.util.List;

/**
 * @author Karl Bennett
 */
public interface Servlets {

    List<Class<? extends Servlet>> getServlets();
}
