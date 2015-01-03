package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;

import javax.servlet.Servlet;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class ServletServletsFactory implements Factory<List<Class<? extends Servlet>>, Servlets> {

    @SuppressWarnings("unchecked")
    @Override
    public Servlets create(List<Class<? extends Servlet>> types) {

        return new SettableServlets(types.toArray(new Class[types.size()]));
    }
}
