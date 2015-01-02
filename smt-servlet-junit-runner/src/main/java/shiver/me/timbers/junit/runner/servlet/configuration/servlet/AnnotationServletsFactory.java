package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

import java.util.ArrayList;

/**
 * @author Karl Bennett
 */
public class AnnotationServletsFactory implements ServletsFactory {

    @Override
    public SettableServlets create(Object target) {

        final Class<?> type = target.getClass();

        final ContainerConfiguration configuration = type.getAnnotation(ContainerConfiguration.class);

        if (null == configuration) {
            return new SettableServlets(new ArrayList<ServletDetail>());
        }

        return new SettableServlets(configuration.servlets());
    }
}