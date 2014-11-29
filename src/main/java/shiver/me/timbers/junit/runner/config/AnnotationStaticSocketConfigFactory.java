package shiver.me.timbers.junit.runner.config;

import shiver.me.timbers.junit.runner.annotation.Port;

import static shiver.me.timbers.junit.runner.config.NullSocketConfig.NULL_PORT_CONFIG;

/**
 * This factory will return a port config that sets the port according to the value in the test classes
 * {@link shiver.me.timbers.junit.runner.annotation.Port} annotation.
 *
 * @author Karl Bennett
 */
public class AnnotationStaticSocketConfigFactory implements StaticSocketConfigFactory {

    @Override
    public SocketConfig create(Object target) {

        final Class type = target.getClass();

        final Port port = (Port) type.getAnnotation(Port.class);

        if (null == port) {
            return NULL_PORT_CONFIG;
        }

        return new SettableSocketConfig(port.value());
    }
}
