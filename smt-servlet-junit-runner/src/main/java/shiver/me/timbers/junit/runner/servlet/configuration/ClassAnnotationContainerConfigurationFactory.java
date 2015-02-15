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
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

import static shiver.me.timbers.junit.runner.servlet.Reflections.instantiate;
import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class ClassAnnotationContainerConfigurationFactory<C> implements ContainerConfigurationFactory<C> {

    private final Logger log = LoggerFactory.getLogger(ClassAnnotationContainerConfigurationFactory.class);

    private final AnnotationExtractor<shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration> annotationExtractor;

    public ClassAnnotationContainerConfigurationFactory(
            AnnotationExtractor<shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration> annotationExtractor
    ) {
        this.annotationExtractor = annotationExtractor;

        log.debug("Constructed");
    }

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {

        final Class<?> type = target.getClass();

        final shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration =
                annotationExtractor.create(type);

        if (notConfigured(containerConfiguration)) {
            log.warn("No container configuration found on {}", target);
            return NULL_CONTAINER_CONFIG;
        }

        final Class<? extends ContainerConfiguration> configurationClass = containerConfiguration.value();

        log.debug("Container configuration found on {}. Instantiating new {}", target, configurationClass);
        return instantiate(configurationClass);
    }

    private static boolean notConfigured(
            shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration containerConfiguration) {
        return null == containerConfiguration || containerConfiguration.value().equals(NullContainerConfiguration.class);
    }
}
