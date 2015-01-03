package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.Packages;
import shiver.me.timbers.junit.runner.servlet.Servlets;

/**
 * This factory will create a {@link Servlets} instance that contains a
 * {@link shiver.me.timbers.junit.runner.servlet.ServletDetail} for evey {@link javax.servlet.Servlet} contained in the
 * {@link Packages} resources.
 *
 * @author Karl Bennett
 */
public interface PackagesServletsFactory {
    Servlets create(Packages packages);
}
