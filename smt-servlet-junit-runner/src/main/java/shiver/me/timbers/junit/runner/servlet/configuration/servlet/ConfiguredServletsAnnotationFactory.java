package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.ServletConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class ConfiguredServletsAnnotationFactory implements AnnotationFactory<ContainerConfiguration, Servlets> {

    @Override
    public Servlets create(ContainerConfiguration annotation) {

        final ServletConfiguration[] configurations = annotation.servletConfigurations();

        final List<ServletDetail> servletDetails = new ArrayList<>();
        for (ServletConfiguration configuration : configurations) {
            servletDetails.add(new ServletDetail(configuration.servlet(), configuration.configuration()));
        }

        return new SettableServlets(servletDetails);
    }
}
