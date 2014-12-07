package shiver.me.timbers.junit.runner.servlet.configuration;

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
