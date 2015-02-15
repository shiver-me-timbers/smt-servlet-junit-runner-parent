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

/**
 * @author Karl Bennett
 */
public class FreeRandomPortConfigurationFactory implements RandomPortConfigurationFactory {

    private final Logger log = LoggerFactory.getLogger(FreeRandomPortConfigurationFactory.class);

    @Override
    public PortConfiguration create() {

        final SocketPortConfiguration configuration = new SocketPortConfiguration();

        log.debug("Random port configured to be {}", configuration.getPort());
        return configuration;
    }
}
