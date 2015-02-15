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

import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

/**
 * This ContainerConfigurationFactory will try each of it's configured factories until one of them returns a custom
 * configuration.
 *
 * @author Karl Bennett
 */
public class ConsecutiveContainerConfigurationFactory<C> implements ContainerConfigurationFactory<C> {

    private final Logger log = LoggerFactory.getLogger(ConsecutiveContainerConfigurationFactory.class);

    private final ContainerConfigurationFactory<C>[] containerConfigFactories;

    @SafeVarargs
    public ConsecutiveContainerConfigurationFactory(ContainerConfigurationFactory<C>... containerConfigFactories) {
        this.containerConfigFactories = containerConfigFactories;

        log.debug("Constructed");
    }

    @SuppressWarnings("unchecked")
    @Override
    public ContainerConfiguration<C> create(Object target) {
        log.debug("Searching for configurations on {}", target);
        for (ContainerConfigurationFactory<C> containerConfigurationFactory : containerConfigFactories) {
            log.debug("Trying {} on {}", containerConfigurationFactory, target);
            final ContainerConfiguration<C> config = containerConfigurationFactory.create(target);

            if (!NULL_CONTAINER_CONFIG.equals(config)) {
                log.debug("Found configuration with {} on {}", containerConfigurationFactory, target);
                return config;
            }
        }
        log.warn("No configuration found on {}", target);
        return NULL_CONTAINER_CONFIG;
    }
}
