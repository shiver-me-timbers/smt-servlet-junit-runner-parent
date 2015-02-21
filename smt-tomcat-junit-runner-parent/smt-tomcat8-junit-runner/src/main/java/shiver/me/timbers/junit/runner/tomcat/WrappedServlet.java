package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Wrapper;

/**
 * @author Karl Bennett
 */
class WrappedServlet implements ServletWrapper {

    private final Wrapper wrapper;

    public WrappedServlet(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void setLoadOnStartup(int value) {
        wrapper.setLoadOnStartup(value);
    }

    @Override
    public void setAsyncSupported(boolean asyncSupported) {
        wrapper.setAsyncSupported(asyncSupported);
    }

    @Override
    public void addMapping(String mapping) {
        wrapper.addMapping(mapping);
    }

    @Override
    public void addInitParameter(String name, String value) {
        wrapper.addInitParameter(name, value);
    }
}
