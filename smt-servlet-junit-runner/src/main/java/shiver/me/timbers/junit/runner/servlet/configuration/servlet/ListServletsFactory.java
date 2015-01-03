package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;

import java.util.List;

/**
 * @author Karl Bennett
 */
public class ListServletsFactory implements Factory<List<Servlets>, Servlets> {

    @Override
    public Servlets create(List<Servlets> servletses) {
        return new SettableServlets(servletses.toArray(new Servlets[servletses.size()]));
    }
}
