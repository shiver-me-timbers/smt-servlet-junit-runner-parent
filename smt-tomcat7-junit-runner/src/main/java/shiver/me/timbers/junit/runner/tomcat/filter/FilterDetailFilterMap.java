package shiver.me.timbers.junit.runner.tomcat.filter;

import org.apache.catalina.deploy.FilterMap;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;

import javax.servlet.DispatcherType;

/**
 * @author Karl Bennett
 */
public class FilterDetailFilterMap extends FilterMap {

    public FilterDetailFilterMap(FilterDetail filterDetail) {

        setFilterName(filterDetail.getFilterName());

        for (String servletName : filterDetail.getServletNames()) {
            addServletName(servletName);
        }

        for (String urlPattern : filterDetail.getUrlPatterns()) {
            addURLPattern(urlPattern);
        }

        // Each call to FilterMap.setDispatcher(String) with a different DispatcherType actually sets a bit flag for
        // each type.
        for (DispatcherType dispatcherType : filterDetail.getDispatcherTypes()) {
            setDispatcher(dispatcherType.name());
        }
    }
}
