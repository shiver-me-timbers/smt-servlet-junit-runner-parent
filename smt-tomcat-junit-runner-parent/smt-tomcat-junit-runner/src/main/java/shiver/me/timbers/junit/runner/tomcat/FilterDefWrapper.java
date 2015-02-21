package shiver.me.timbers.junit.runner.tomcat;

import javax.servlet.Filter;

/**
 * @author Karl Bennett
 */
public interface FilterDefWrapper {

    void setFilter(Filter filter);

    void setDescription(String description);

    void setDisplayName(String displayName);

    void setFilterName(String filterName);

    void setSmallIcon(String smallIcon);

    void setLargeIcon(String largeIcon);

    void setAsyncSupported(String asyncSupported);

    void addInitParameter(String name, String value);
}
