package shiver.me.timbers.junit.runner.servlet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static java.lang.String.format;

/**
 * Simple reflection utilities.
 *
 * @author Karl Bennett
 */
public class Reflections {

    public static <T> T instantiate(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(
                    format(
                            "The %s implementation must have a public default constructor.",
                            type.getName()
                    ),
                    e
            );
        }
    }

    public static <T> T instantiate(Class<T> type, Object... args) {

        final Class[] types = types(args);

        try {

            final Constructor<T> constructor = type.getConstructor(types);

            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException(
                    format(
                            "The %s implementation does not have a constructor with args (%s).",
                            type.getName(), Arrays.toString(types)
                    ),
                    e
            );
        }
    }

    public static Method getMethod(Object object, String name, Object... args) {

        return getMethod(object.getClass(), name, args);
    }

    public static Method getMethod(Class<?> type, String name, Object... args) {

        return getMethod(type, name, types(args));
    }

    public static Method getMethod(Class<?> type, String name, Class... args) {

        try {
            return type.getMethod(name, args);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    format(
                            "Class %s does not have a method (%s) with arguments (%s).",
                            type.getName(), name, Arrays.toString(args)
                    ),
                    e
            );
        }
    }

    private static Class[] types(Object... args) {

        final Class[] types = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }

        return types;
    }

    @SuppressWarnings("unchecked")
    public static <T> T callMethod(Object object, Method method, Object... args) {

        try {
            return (T) method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(
                    format(
                            "The %s method does not have the arguments (%s).",
                            object.getClass().getName(), Arrays.toString(args)
                    ),
                    e
            );
        }
    }
}
