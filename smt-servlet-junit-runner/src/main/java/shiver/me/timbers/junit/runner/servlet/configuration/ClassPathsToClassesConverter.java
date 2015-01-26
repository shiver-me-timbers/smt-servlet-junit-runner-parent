package shiver.me.timbers.junit.runner.servlet.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class ClassPathsToClassesConverter implements ClassPathsConverter<List<Class>> {

    @Override
    public List<Class> create(List<String> classPaths) {

        final List<Class> classes = new ArrayList<>();

        for (String classPath : classPaths) {

            if (isClassFile(classPath)) {
                classes.add(toClass(classPath));
            }
        }

        return classes;
    }

    private static boolean isClassFile(String classPath) {
        return classPath.endsWith(".class") && !classPath.endsWith("package-info.class");
    }

    private static Class toClass(String classPath) {

        final String className = classPath.replace(".class", "").replaceAll("/", ".");

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
