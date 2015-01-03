package shiver.me.timbers.junit.runner.servlet.configuration.servlet;

import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.SettablePackages;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

/**
 * @author Karl Bennett
 */
public class ServletsPackagesAnnotationFactory implements AnnotationFactory<ContainerConfiguration, Servlets> {

    private final PackagesServletsFactory packagesServletsFactory;

    public ServletsPackagesAnnotationFactory(PackagesServletsFactory packagesServletsFactory) {
        this.packagesServletsFactory = packagesServletsFactory;
    }

    @Override
    public Servlets create(ContainerConfiguration configuration) {

        return packagesServletsFactory.create(new SettablePackages(configuration.packages()));
    }
}
