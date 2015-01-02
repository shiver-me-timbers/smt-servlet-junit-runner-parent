package shiver.me.timbers.junit.runner.servlet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class CompositeServletsFactory implements ServletsFactory {

    private final ServletsFactory[] factories;

    public CompositeServletsFactory(ServletsFactory... factories) {
        this.factories = factories;
    }

    @Override
    public Servlets create(Object target) {

        final List<Servlets> list = new ArrayList<>();

        for (ServletsFactory factory : factories) {

            list.add(factory.create(target));
        }

        return new SettableServlets(list.toArray(new Servlets[list.size()]));
    }
}
