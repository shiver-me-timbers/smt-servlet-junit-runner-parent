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

package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationExtractionFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ClassPathsToClassesConverter;
import shiver.me.timbers.junit.runner.servlet.configuration.CompositeContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.PackagesAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ResourceClassPathsFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ResourcePackagesFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.SubTypeFilter;
import shiver.me.timbers.junit.runner.servlet.inject.ClassAnnotationExtractor;

import javax.servlet.Filter;


/**
 * @author Karl Bennett
 */
public class FiltersContainerConfigurationAnnotationFactory
        extends AnnotationExtractionFactory<ContainerConfiguration, Filters> implements FiltersFactory {

    private final Logger log = LoggerFactory.getLogger(FiltersContainerConfigurationAnnotationFactory.class);

    public FiltersContainerConfigurationAnnotationFactory() {
        super(
                new ClassAnnotationExtractor<>(ContainerConfiguration.class),
                new FiltersEmptyFactory(),
                new CompositeContainerConfigurationAnnotationFactory<>(
                        new ListFiltersFactory(),
                        new FiltersAnnotationFactory(),
                        new PackagesAnnotationFactory<>(
                                new ResourcePackagesFactory<>(
                                        new ResourceClassPathsFactory(),
                                        new ClassPathsToClassesConverter(),
                                        new SubTypeFilter<>(Filter.class),
                                        new FilterFiltersFactory()
                                )
                        ),
                        new ConfiguredFiltersAnnotationFactory()
                )
        );

        log.debug("Constructed");
    }
}
