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

package shiver.me.timbers.junit.runner.servlet.configuration.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

/**
 * This factory will return a port configuration that sets the port according to the value in the test classes
 * {@link shiver.me.timbers.junit.runner.servlet.annotation.Port} annotation.
 *
 * @author Karl Bennett
 */
public class AnnotationStaticPortConfigurationFactory implements StaticPortConfigurationFactory {

    private final Logger log = LoggerFactory.getLogger(AnnotationStaticPortConfigurationFactory.class);

    private final AnnotationExtractor<ContainerConfiguration> annotationExtractor;

    public AnnotationStaticPortConfigurationFactory(AnnotationExtractor<ContainerConfiguration> annotationExtractor) {
        this.annotationExtractor = annotationExtractor;

        log.debug("Constructed");
    }

    @Override
    public PortConfiguration create(Object target) {

        final Class<?> type = target.getClass();

        final ContainerConfiguration configuration = annotationExtractor.create(type);

        if (portNotSet(configuration)) {
            log.debug("Port is not configured");
            return NullPortConfiguration.NULL_PORT_CONFIG;
        }

        final int port = configuration.port();

        log.debug("Port configured as {}", port);
        return new SocketPortConfiguration(port);
    }

    private static boolean portNotSet(ContainerConfiguration configuration) {
        return null == configuration || -1 >= configuration.port();
    }
}
