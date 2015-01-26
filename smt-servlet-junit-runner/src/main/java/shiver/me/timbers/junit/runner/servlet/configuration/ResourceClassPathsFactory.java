package shiver.me.timbers.junit.runner.servlet.configuration;

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
 * @author Karl Bennett
 */
public class ResourceClassPathsFactory implements ClassPathsFactory {

    private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

    @Override
    public List<String> create(Packages input) {

        final ArrayList<String> fileNames = new ArrayList<>();

        for (String pkg : input) {

            final String packagePath = toPath(pkg);

            final URL resource = toResource(packagePath);

            checkPackageExists(resource);

            final String resourcePath = resource.getPath();

            if (isInJar(resourcePath)) {

                fileNames.addAll(filesInJarPath(resourcePath));

            } else {

                fileNames.addAll(filesInPath(resourcePath, packagePath));
            }

        }

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

        for (File file : pkg.listFiles()) {

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
