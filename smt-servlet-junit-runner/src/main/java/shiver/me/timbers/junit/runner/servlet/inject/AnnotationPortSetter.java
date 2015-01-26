package shiver.me.timbers.junit.runner.servlet.inject;

import shiver.me.timbers.junit.runner.servlet.annotation.Port;
import shiver.me.timbers.junit.runner.servlet.configuration.port.PortConfiguration;

import java.lang.reflect.Field;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class AnnotationPortSetter implements PortSetter {

    @Override
    public void set(Object target, PortConfiguration portConfiguration) {

        final Class<?> type = target.getClass();

        final Field field = findPortField(type);

        if (null == field) {
            return;
        }

        field.setAccessible(true);

        final int port = portConfiguration.getPort();

        setPort(target, field, port);
    }

    private static Field findPortField(Class<?> type) {

        final Field[] fields = type.getDeclaredFields();

        for (Field field : fields) {

            if (null != field.getAnnotation(Port.class)) {
                return field;
            }
        }

        if (Object.class.equals(type)) {
            return null;
        }

        return findPortField(type.getSuperclass());
    }

    static void setPort(Object target, Field field, int port) {
        try {
            field.set(target, port);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(
                    format("The field (%s) must have the type int or Integer.", field.getName()),
                    e
            );
        }
    }
}
