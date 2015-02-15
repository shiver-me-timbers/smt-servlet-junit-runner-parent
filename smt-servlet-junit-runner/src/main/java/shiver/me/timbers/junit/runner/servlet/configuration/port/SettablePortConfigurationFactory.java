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

import static shiver.me.timbers.junit.runner.servlet.configuration.port.NullPortConfiguration.NULL_PORT_CONFIG;

/**
 * @author Karl Bennett
 */
public class SettablePortConfigurationFactory implements PortConfigurationFactory {

    private final Logger log = LoggerFactory.getLogger(SettablePortConfigurationFactory.class);

    private final StaticPortConfigurationFactory staticPortConfigFactory;
    private final RandomPortConfigurationFactory randomPortConfigurationFactory;

    public SettablePortConfigurationFactory(StaticPortConfigurationFactory staticPortConfigFactory,
                                            RandomPortConfigurationFactory randomPortConfigurationFactory) {
        this.staticPortConfigFactory = staticPortConfigFactory;
        this.randomPortConfigurationFactory = randomPortConfigurationFactory;

        log.debug("Constructed");
    }

    @Override
    public PortConfiguration create(Object target) {

        final PortConfiguration config = staticPortConfigFactory.create(target);

        if (NULL_PORT_CONFIG.equals(config)) {
            log.debug("Port not manually set, choosing one randomly");
            return randomPortConfigurationFactory.create();
        }
        log.debug("Port manually set to {}", config.getPort());
        return config;
    }
}
