package shiver.me.timbers.junit.runner.servlet;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class SettablePackages implements Packages {

    private static List<URL> transform(String... packages) {

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        final List<URL> urls = new ArrayList<>(packages.length);

        for (String pkg : packages) {

            final URL url = loader.getResource(toPath(pkg));

            if (null == url) {
                throw new IllegalArgumentException(format("The package (%s) does not exist.", pkg));
            }

            urls.add(url);
        }

        return urls;
    }

    private static String toPath(String pkg) {
        return pkg.replaceAll("\\.", "/");
    }

    private final List<URL> packages;

    public SettablePackages(String... packages) {
        this(transform(packages));
    }

    public SettablePackages(List<URL> packages) {
        this.packages = packages;
    }

    @Override
    public List<URL> asList() {
        return packages;
    }

    @Override
    public Iterator<URL> iterator() {
        return packages.iterator();
    }
}
