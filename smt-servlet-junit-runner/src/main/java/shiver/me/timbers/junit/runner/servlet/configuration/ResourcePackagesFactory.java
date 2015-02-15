package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Packages;

import java.util.List;

/**
 * @author Karl Bennett
 */
public class ResourcePackagesFactory<FI, FO, O> implements PackagesFactory<O> {

    private final Logger log = LoggerFactory.getLogger(ResourcePackagesFactory.class);

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

        log.debug("Constructed");
    }

    @Override
    public O create(Packages packages) {

        log.debug("Getting class paths for {} with {}", packages, classPathsFactory);
        final List<String> fileNames = classPathsFactory.create(packages);

        log.debug("Converting class paths {} with {}", packages, classPathsConverter);
        final FI conversions = classPathsConverter.create(fileNames);

        log.debug("Filtering conversions {} with {}", packages, filter);
        final FO filtered = filter.create(conversions);

        log.debug("Final conversion of {} with {}", filtered, converter);
        return converter.create(filtered);
    }
}
