package shiver.me.timbers.junit.runner.servlet.config;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Karl Bennett
 */
public class SocketPortConfig implements PortConfig {

    private static ServerSocket socket(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final ServerSocket socket;

    public SocketPortConfig() {
        this(0);
    }

    public SocketPortConfig(int port) {
        this(socket(port));
    }

    public SocketPortConfig(ServerSocket socket) {
        this.socket = socket;
    }

    @Override
    public int getPort() {
        return socket.getLocalPort();
    }
}
