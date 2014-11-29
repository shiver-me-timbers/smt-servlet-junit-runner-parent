package shiver.me.timbers.junit.runner.config;

import java.net.ServerSocket;

/**
 * @author Karl Bennett
 */
public class NullSocketConfig implements SocketConfig {

    public static final NullSocketConfig NULL_PORT_CONFIG = new NullSocketConfig();

    private NullSocketConfig() {
    }

    @Override
    public ServerSocket getSocket() {
        return null;
    }
}
