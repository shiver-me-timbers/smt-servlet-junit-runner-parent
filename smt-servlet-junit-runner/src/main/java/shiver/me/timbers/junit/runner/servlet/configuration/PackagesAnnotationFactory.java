package shiver.me.timbers.junit.runner.servlet.configuration;

import shiver.me.timbers.junit.runner.servlet.SettablePackages;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

/**
 * @author Karl Bennett
 */
public class PackagesAnnotationFactory<T> implements AnnotationFactory<ContainerConfiguration, T> {

    private final PackagesFactory<T> packagesFactory;

    public PackagesAnnotationFactory(PackagesFactory<T> packagesFactory) {
        this.packagesFactory = packagesFactory;
    }

    @Override
    public T create(ContainerConfiguration configuration) {

        return packagesFactory.create(new SettablePackages(configuration.packages()));
    }
}
