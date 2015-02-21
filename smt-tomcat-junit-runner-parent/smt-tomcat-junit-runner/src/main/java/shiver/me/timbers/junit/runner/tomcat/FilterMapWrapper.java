package shiver.me.timbers.junit.runner.tomcat;

/**
 * @author Karl Bennett
 */
public interface FilterMapWrapper {

    void setFilterName(String filterName);

    void addServletName(String servletName);

    void addURLPattern(String urlPattern);

    void setDispatcher(String dispatcherType);
}
