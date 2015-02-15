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

package shiver.me.timbers.junit.runner.servlet;

import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.ClassAnnotationContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.ConsecutiveContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.MethodAnnotationContainerConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.WebXmlContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.filter.FiltersContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.AnnotationStaticPortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.FreeRandomPortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.port.SettablePortConfigurationFactory;
import shiver.me.timbers.junit.runner.servlet.configuration.servlet.ServletsContainerConfigurationAnnotationFactory;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationPortSetter;
import shiver.me.timbers.junit.runner.servlet.inject.ClassAnnotationExtractor;

/**
 * This can be extended to create a new container specific implementation the {@link ServletJUnitRunner}.
 *
 * @author Karl Bennett
 */
public class AnnotationServletJUnitRunner<C> extends ServletJUnitRunner<C> {

    private final Logger log = LoggerFactory.getLogger(AnnotationServletJUnitRunner.class);

    private static final ClassAnnotationExtractor<ContainerConfiguration> CLASS_ANNOTATION_EXTRACTOR =
            new ClassAnnotationExtractor<>(ContainerConfiguration.class);

    public AnnotationServletJUnitRunner(Container<C> container, Class test) throws InitializationError {
        super(
                new SettablePortConfigurationFactory(
                        new AnnotationStaticPortConfigurationFactory(CLASS_ANNOTATION_EXTRACTOR),
                        new FreeRandomPortConfigurationFactory()
                ),
                new ServletsContainerConfigurationAnnotationFactory(),
                new FiltersContainerConfigurationAnnotationFactory(),
                new WebXmlContainerConfigurationAnnotationFactory(),
                new ConsecutiveContainerConfigurationFactory<>(
                        new ClassAnnotationContainerConfigurationFactory<C>(CLASS_ANNOTATION_EXTRACTOR),
                        new MethodAnnotationContainerConfigurationFactory<C>()
                ),
                new AnnotationPortSetter(),
                new ShutdownRunListenerFactory(),
                container,
                test
        );

        log.debug("Constructed");
    }
}
