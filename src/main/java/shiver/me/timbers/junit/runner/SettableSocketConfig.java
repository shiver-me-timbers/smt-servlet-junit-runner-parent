package shiver.me.timbers.junit.runner;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Karl Bennett
 */
public class SettableSocketConfig implements SocketConfig {

    private static ServerSocket socket(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final ServerSocket socket;

    public SettableSocketConfig() {
        this(0);
    }

    public SettableSocketConfig(int port) {
        this(socket(port));
    }

    public SettableSocketConfig(ServerSocket socket) {
        this.socket = socket;
    }

    @Override
    public ServerSocket getSocket() {
        return socket;
    }
}
