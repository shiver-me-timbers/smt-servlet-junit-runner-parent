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

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Karl Bennett
 */
public class SocketPortConfiguration implements PortConfiguration {

    static int socketPort(int port) {
        try {
            final ServerSocket socket = new ServerSocket(port);

            final int actualPort = socket.getLocalPort();

            socket.close();

            return actualPort;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final int port;

    public SocketPortConfiguration() {
        this(0);
    }

    public SocketPortConfiguration(int port) {
        this.port = socketPort(port);
    }

    @Override
    public int getPort() {
        return port;
    }
}
