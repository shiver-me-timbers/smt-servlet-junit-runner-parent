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

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SocketPortConfigurationTest {

    @Test
    public void A_random_port_can_be_set() {

        assertThat(new SocketPortConfiguration().getPort(), greaterThan(0));
    }

    @Test
    public void A_specific_port_can_be_set() {

        final int port = 9998;

        assertEquals(port, new SocketPortConfiguration(port).getPort());
    }

    @Test(expected = RuntimeException.class)
    public void An_invalid_port_cannot_be_set() {

        new SocketPortConfiguration(80);  // Root privileges are required to use port 80.
    }
}
