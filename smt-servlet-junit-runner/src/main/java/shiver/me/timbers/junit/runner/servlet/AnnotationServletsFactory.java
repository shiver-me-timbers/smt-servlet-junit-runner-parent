package shiver.me.timbers.junit.runner.servlet;

import shiver.me.timbers.junit.runner.servlet.annotation.Servlets;

import java.util.ArrayList;

/**
 * @author Karl Bennett
 */
public class AnnotationServletsFactory implements ServletsFactory {

    @Override
    public SettableServlets create(Object target) {

        final Class<?> type = target.getClass();

        final Servlets servlets = type.getAnnotation(Servlets.class);

        if (null == servlets) {
            return new SettableServlets(new ArrayList<ServletDetails>());
        }

        return new SettableServlets(servlets.value());
    }
}
