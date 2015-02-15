package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;

import javax.servlet.Servlet;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class ServletServletsFactory implements Factory<List<Class<? extends Servlet>>, Servlets> {

    private final Logger log = LoggerFactory.getLogger(ServletServletsFactory.class);

    @SuppressWarnings("unchecked")
    @Override
    public Servlets create(List<Class<? extends Servlet>> types) {
        log.debug("Servlets created from {}", types);
        return new SettableServlets(types.toArray(new Class[types.size()]));
    }
}
