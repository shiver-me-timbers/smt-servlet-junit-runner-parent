package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.EmptyFactory;
import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;

import java.util.ArrayList;

/**
 * @author Karl Bennett
 */
public class ServletsEmptyFactory implements EmptyFactory<Servlets> {

    private final Logger log = LoggerFactory.getLogger(ServletsEmptyFactory.class);

    @Override
    public Servlets create() {
        log.warn("No servlets created.");
        return new SettableServlets(new ArrayList<ServletDetail>());
    }
}
