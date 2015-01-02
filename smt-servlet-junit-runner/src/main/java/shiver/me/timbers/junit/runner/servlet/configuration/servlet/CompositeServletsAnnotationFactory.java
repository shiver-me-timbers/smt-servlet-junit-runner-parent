package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class CompositeServletsAnnotationFactory implements AnnotationFactory<ContainerConfiguration, Servlets> {

    private final AnnotationFactory<ContainerConfiguration, Servlets>[] factories;

    @SafeVarargs
    public CompositeServletsAnnotationFactory(AnnotationFactory<ContainerConfiguration, Servlets>... factories) {
        this.factories = factories;
    }

    @Override
    public Servlets create(ContainerConfiguration configuration) {

        final List<Servlets> list = new ArrayList<>();

        for (AnnotationFactory<ContainerConfiguration, Servlets> factory : factories) {

            list.add(factory.create(configuration));
        }

        return new SettableServlets(list.toArray(new Servlets[list.size()]));
    }
}
