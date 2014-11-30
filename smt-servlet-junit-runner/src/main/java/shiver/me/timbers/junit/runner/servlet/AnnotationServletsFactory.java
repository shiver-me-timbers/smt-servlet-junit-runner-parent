package shiver.me.timbers.junit.runner.servlet;

import shiver.me.timbers.junit.runner.servlet.annotation.Servlets;

import javax.servlet.Servlet;
import java.util.ArrayList;

import static java.util.Arrays.asList;

/**
 * @author Karl Bennett
 */
public class AnnotationServletsFactory implements ServletsFactory {

    @Override
    public SettableServlets create(Object target) {

        final Class<?> type = target.getClass();

        final Servlets servlets = type.getAnnotation(Servlets.class);

        if (null == servlets) {
            return new SettableServlets(new ArrayList<Class<? extends Servlet>>());
        }

        return new SettableServlets(asList(servlets.value()));
    }
}
