package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.SettablePackages;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;

/**
 * @author Karl Bennett
 */
public class PackagesAnnotationFactory<T> implements AnnotationFactory<ContainerConfiguration, T> {

    private final Logger log = LoggerFactory.getLogger(PackagesAnnotationFactory.class);

    private final PackagesFactory<T> packagesFactory;

    public PackagesAnnotationFactory(PackagesFactory<T> packagesFactory) {
        this.packagesFactory = packagesFactory;

        log.debug("Constructed");
    }

    @Override
    public T create(ContainerConfiguration configuration) {

        final String[] packages = configuration.packages();

        log.debug("Creating instances from {}", packages);
        return packagesFactory.create(new SettablePackages(packages));
    }
}
