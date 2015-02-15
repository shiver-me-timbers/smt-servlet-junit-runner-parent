/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
