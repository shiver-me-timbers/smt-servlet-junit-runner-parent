package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

/**
 * @author Karl Bennett
 */
public class ServletsAnnotationFactory implements AnnotationFactory<ContainerConfiguration, Servlets> {

    @Override
    public Servlets create(ContainerConfiguration configuration) {
        return new SettableServlets(configuration.servlets());
    }
}
