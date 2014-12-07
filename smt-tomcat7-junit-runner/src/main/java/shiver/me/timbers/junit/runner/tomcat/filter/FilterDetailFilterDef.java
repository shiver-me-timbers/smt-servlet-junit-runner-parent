package shiver.me.timbers.junit.runner.tomcat.filter;

import org.apache.catalina.deploy.FilterDef;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;

import static java.util.Map.Entry;

/**
 * @author Karl Bennett
 */
public class FilterDetailFilterDef extends FilterDef {

    public FilterDetailFilterDef(FilterDetail filterDetail) {

        setFilter(filterDetail.getFilter());
        setDescription(filterDetail.getDescription());
        setDisplayName(filterDetail.getDisplayName());
        setFilterName(filterDetail.getFilterName());
        setSmallIcon(filterDetail.getSmallIcon());
        setLargeIcon(filterDetail.getLargeIcon());
        setAsyncSupported(String.valueOf(filterDetail.asyncSupported()));

        for (Entry<String, String> initParam : filterDetail.getInitParams().entrySet()) {
            addInitParameter(initParam.getKey(), initParam.getValue());
        }
    }
}
