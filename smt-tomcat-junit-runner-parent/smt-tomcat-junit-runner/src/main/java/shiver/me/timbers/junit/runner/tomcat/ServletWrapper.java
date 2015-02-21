package shiver.me.timbers.junit.runner.tomcat;

/**
 * @author Karl Bennett
 */
public interface ServletWrapper {

    void setLoadOnStartup(int value);

    void setAsyncSupported(boolean asyncSupported);

    void addMapping(String mapping);

    void addInitParameter(String name, String value);
}
