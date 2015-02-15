package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(ConfiguredServletsAnnotationFactory.class);

    @Override
    public Servlets create(ContainerConfiguration annotation) {

        final ServletConfiguration[] configurations = annotation.servletConfigurations();

        log.debug("Adding configured servlets");
        final List<ServletDetail> servletDetails = new ArrayList<>();
        for (ServletConfiguration configuration : configurations) {
            log.debug("Adding {}", configuration);
            servletDetails.add(new ServletDetail(configuration.servlet(), configuration.configuration()));
        }
        log.debug("Added {}", servletDetails);
        return new SettableServlets(servletDetails);
    }
}
