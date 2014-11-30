package shiver.me.timbers.junit.runner;

import javax.servlet.Servlet;
import java.util.List;

/**
 * @author Karl Bennett
 */
public interface Servlets {

    List<Class<? extends Servlet>> getServlets();
}
