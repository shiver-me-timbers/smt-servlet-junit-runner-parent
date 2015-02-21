package shiver.me.timbers.junit.runner.tomcat;

import org.apache.tomcat.JarScanner;
import org.apache.tomcat.JarScannerCallback;

import javax.servlet.ServletContext;
import java.util.Set;

/**
 * @author Karl Bennett
 */
class NullJarScanner implements JarScanner {

    @Override
    public void scan(ServletContext context, ClassLoader classloader, JarScannerCallback callback,
                     Set<String> jarsToSkip) {
    }
}
