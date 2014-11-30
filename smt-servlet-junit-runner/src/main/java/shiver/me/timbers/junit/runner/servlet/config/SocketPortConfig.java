package shiver.me.timbers.junit.runner.servlet.config;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Karl Bennett
 */
public class SocketPortConfig implements PortConfig {

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

    public SocketPortConfig() {
        this(0);
    }

    public SocketPortConfig(int port) {
        this.port = socketPort(port);
    }

    @Override
    public int getPort() {
        return port;
    }
}
