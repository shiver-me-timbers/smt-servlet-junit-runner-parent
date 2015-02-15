package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class ClassPathsToClassesConverter implements ClassPathsConverter<List<Class>> {

    private final Logger log = LoggerFactory.getLogger(ClassPathsToClassesConverter.class);

    @Override
    public List<Class> create(List<String> classPaths) {

        final List<Class> classes = new ArrayList<>();

        log.debug("Converting {}, to classes", classPaths);
        for (String classPath : classPaths) {

            if (isClassFile(classPath)) {

                log.debug("Converting {}, to class", classPath);
                final Class type = toClass(classPath);

                log.debug("Converted {}, to {}", classPath, type);
                classes.add(type);
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
