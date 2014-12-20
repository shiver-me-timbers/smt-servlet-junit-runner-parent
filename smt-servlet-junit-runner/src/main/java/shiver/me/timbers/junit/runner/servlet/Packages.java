package shiver.me.timbers.junit.runner.servlet;

import java.net.URL;
import java.util.List;

/**
 * @author Karl Bennett
 */
public interface Packages extends Iterable<URL> {

    List<URL> asList();
}
