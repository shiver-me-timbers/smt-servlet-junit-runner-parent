package shiver.me.timbers.junit.runner.servlet.configuration;

import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Packages;

import java.util.List;

/**
 * @author Karl Bennett
 */
public class ResourcePackagesFactory<FI, FO, O> implements PackagesFactory<O> {

    private final ClassPathsFactory classPathsFactory;
    private final ClassPathsConverter<FI> classPathsConverter;
    private final Factory<FI, FO> filter;
    private final Factory<FO, O> converter;

    public ResourcePackagesFactory(
            ClassPathsFactory classPathsFactory,
            ClassPathsConverter<FI> classPathsConverter,
            Factory<FI, FO> filter,
            Factory<FO, O> converter
    ) {
        this.classPathsFactory = classPathsFactory;
        this.classPathsConverter = classPathsConverter;
        this.filter = filter;
        this.converter = converter;
    }

    @Override
    public O create(Packages packages) {

        final List<String> fileNames = classPathsFactory.create(packages);

        final FI conversions = classPathsConverter.create(fileNames);

        final FO filtered = filter.create(conversions);

        return converter.create(filtered);
    }
}
