package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.ServletDetail;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettableServlets;
import shiver.me.timbers.junit.runner.servlet.configuration.EmptyFactory;

import java.util.ArrayList;

/**
 * @author Karl Bennett
 */
public class ServletsEmptyFactory implements EmptyFactory<Servlets> {

    @Override
    public Servlets create() {
        return new SettableServlets(new ArrayList<ServletDetail>());
    }
}
