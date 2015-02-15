package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;

import java.util.List;

/**
 * @author Karl Bennett
 */
public class ListServletsFactory implements Factory<List<Servlets>, Servlets> {

    private final Logger log = LoggerFactory.getLogger(ListServletsFactory.class);

    @Override
    public Servlets create(List<Servlets> servletses) {
        log.debug("Servlets created from {}", servletses);
        return new SettableServlets(servletses.toArray(new Servlets[servletses.size()]));
    }
}
