package shiver.me.timbers.junit.runner.tomcat.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Constants {

    public static final String INIT = "init";
    public static final String PARAM = "param";
    public static final String URL_PATTERN = "/test";
    public static final String SUCCESS = "success";

    public static final String SERVLET_NAME = "test-servlet";
    public static final String PACKAGE_SERVLET_NAME = "test-package-servlet";
    public static final String FILTER_NAME = "test-servlet";

    public static final String PACKAGE_ONE = "shiver.me.timbers.junit.runner.tomcat.test.one";

    public static final URL PACKAGE_ONE_URL = packageToURL(PACKAGE_ONE);

    public static final URL PACKAGE_ONE_DIR_URL = findFile(PACKAGE_ONE_URL);

    public static final String PACKAGE_ONE_DIR_NAME = fileName(PACKAGE_ONE_DIR_URL);

    public static URL packageToURL(String pkg) {
        return Thread.currentThread().getContextClassLoader().getResource(pkg.replaceAll("\\.", "/"));
    }

    private static URL findFile(URL pkg) {

        final String filePath = pkg.toString().replaceFirst("^jar:", "");
        final String path = filePath.replaceFirst("!.*$", "");

        try {
            return new URL(path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fileName(URL url) {
        return new File(url.getPath()).getName();
    }
}
