package shiver.me.timbers.junit.runner;

import shiver.me.timbers.junit.runner.annotation.Port;
import shiver.me.timbers.junit.runner.config.PortConfig;

import java.lang.reflect.Field;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class AnnotationPortSetter implements PortSetter {

    @Override
    public void set(Object target, PortConfig portConfig) {

        final Class<?> type = target.getClass();

        final Field[] fields = type.getDeclaredFields();

        final Field field = findPortField(fields);

        if (null == field) {
            return;
        }

        field.setAccessible(true);

        final int port = portConfig.getPort();

        setPort(target, field, port);
    }

    private static Field findPortField(Field[] fields) {

        for (Field field : fields) {

            if (null != field.getAnnotation(Port.class)) {
                return field;
            }
        }

        return null;
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
