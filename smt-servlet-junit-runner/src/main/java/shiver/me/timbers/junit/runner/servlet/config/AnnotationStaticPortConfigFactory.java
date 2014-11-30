package shiver.me.timbers.junit.runner.servlet.config;

import shiver.me.timbers.junit.runner.servlet.annotation.Port;

import static shiver.me.timbers.junit.runner.servlet.config.NullPortConfig.NULL_PORT_CONFIG;

/**
 * This factory will return a port config that sets the port according to the value in the test classes
 * {@link shiver.me.timbers.junit.runner.servlet.annotation.Port} annotation.
 *
 * @author Karl Bennett
 */
public class AnnotationStaticPortConfigFactory implements StaticPortConfigFactory {

    @Override
    public PortConfig create(Object target) {

        final Class<?> type = target.getClass();

        final Port port = type.getAnnotation(Port.class);

        if (null == port) {
            return NULL_PORT_CONFIG;
        }

        return new SocketPortConfig(port.value());
    }
}
