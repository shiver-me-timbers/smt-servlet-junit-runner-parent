package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

import javax.servlet.Servlet;

/**
 * @author Karl Bennett
 */
public class ServletsAnnotationFactory implements AnnotationFactory<ContainerConfiguration, Servlets> {

    private final Logger log = LoggerFactory.getLogger(ServletsAnnotationFactory.class);

    @Override
    public Servlets create(ContainerConfiguration configuration) {

        final Class<? extends Servlet>[] servlets = configuration.servlets();

        log.debug("Servlets created from {}", servlets);
        return new SettableServlets(servlets);
    }
}
