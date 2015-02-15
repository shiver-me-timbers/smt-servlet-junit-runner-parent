/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
        log.debug("Class paths {} converted to {}", classPaths, classes);
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
