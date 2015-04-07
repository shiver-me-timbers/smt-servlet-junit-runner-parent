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
import shiver.me.timbers.junit.runner.servlet.Packages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static java.lang.String.format;

/**
 * This factory takes package names an returns a list of the class paths to all the files within those packages.
 *
 * @author Karl Bennett
 */
public class ResourceClassPathsFactory implements ClassPathsFactory {

    private final Logger log = LoggerFactory.getLogger(ResourceClassPathsFactory.class);

    private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

    @Override
    public List<String> create(Packages packages) {

        final ArrayList<String> fileNames = new ArrayList<>();
        log.debug("Finding files in {}", packages);
        for (String pkg : packages) {

            final String packagePath = toPath(pkg);

            final URL resource = toResource(packagePath);

            checkPackageExists(resource);

            final String resourcePath = resource.getPath();

            if (isInJar(resourcePath)) {
                log.debug("Finding files in JAR path {}", resourcePath);
                fileNames.addAll(filesInJarPath(resourcePath));

            } else {
                log.debug("Finding files in filesystem path {}", resourcePath);
                fileNames.addAll(filesInPath(resourcePath, packagePath));
            }

        }
        log.debug("All files in {} are {}", packages, fileNames);
        return fileNames;
    }

    private static String toPath(String pkg) {

        return pkg.replaceAll("\\.", "/");
    }

    private static URL toResource(String path) {

        return CLASS_LOADER.getResource(path);
    }

    private static void checkPackageExists(URL resource) {

        if (null == resource) {
            throw new IllegalArgumentException(format("Package (%s) does not exist.", resource));
        }
    }

    private static boolean isInJar(String path) {
        return path.contains(".jar!");
    }

    static List<String> filesInJarPath(String resourcePath) {

        try {
            final String jarFilePrefix = resourcePath.replaceAll("!.*$", "!/");

            final JarFile jarFile = new JarFile(jarFilePrefix.replace("file:", "").replace("!/", ""));

            final String packagePath = resourcePath.replaceAll(jarFilePrefix, "");

            final Enumeration<JarEntry> entries = jarFile.entries();

            final List<String> fileNames = new ArrayList<>();

            while (entries.hasMoreElements()) {

                final JarEntry entry = entries.nextElement();

                final String entryName = entry.getName();

                if (entryName.equals(packagePath) || entryName.equals(packagePath + "/")) {
                    packageIsDirectory(jarFile.getJarEntry(entryName));
                }

                if (!entry.isDirectory() && entryName.startsWith(packagePath)) {
                    fileNames.add(entryName);
                }
            }

            return fileNames;

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void packageIsDirectory(JarEntry pkg) {

        if (!pkg.isDirectory()) {
            throw new IllegalArgumentException(format("Package (%s) is not a directory.", pkg));
        }
    }

    private static List<String> filesInPath(String resourcePath, String packagePath) {

        return filesInPath(resourcePath.replace(packagePath, ""), resourcePath, packagePath);
    }

    private static List<String> filesInPath(String basePath, String resourcePath, String packagePath) {

        final File pkg = new File(resourcePath);

        packageIsDirectory(pkg);

        final List<String> fileNames = new ArrayList<>();

        final File[] files = pkg.listFiles();

        for (File file : files) {

            final String absolutePath = file.getAbsolutePath();

            if (file.isDirectory()) {
                fileNames.addAll(filesInPath(absolutePath, absolutePath.replace(basePath, "")));
            } else {
                fileNames.add(packagePath + "/" + file.getName());
            }
        }

        return fileNames;
    }

    private static void packageIsDirectory(File pkg) {

        if (!pkg.isDirectory()) {
            throw new IllegalArgumentException(format("Package (%s) is not a directory.", pkg));
        }
    }
}
