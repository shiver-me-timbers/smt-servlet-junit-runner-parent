package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static shiver.me.timbers.junit.runner.servlet.Reflections.instantiate;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.buildAnnotation;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.findUrlPatterns;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.transform;

/**
 * @author Karl Bennett
 */
public class FilterDetail {

    private static String findFilterName(Filter filter, WebFilter webFilter) {

        final String name = webFilter.filterName();

        if (name.isEmpty()) {
            return filter.getClass().getSimpleName();
        }

        return name;
    }

    private final String description;
    private final String displayName;
    private final Map<String, String> initParams;
    private final String filterName;
    private final String smallIcon;
    private final String largeIcon;
    private final List<String> servletNames;
    private final List<String> urlPatterns;
    private final List<DispatcherType> dispatcherTypes;
    private final boolean asyncSupported;

    private final Filter filter;

    public FilterDetail(Class<? extends Filter> filter) {
        this(instantiate(filter), buildAnnotation(filter, WebFilter.class, ClassWebFilter.class));
    }

    private FilterDetail(Filter filter, WebFilter webFilter) {
        this(webFilter.description(), webFilter.displayName(), transform(webFilter.initParams()),
                findFilterName(filter, webFilter), webFilter.smallIcon(), webFilter.largeIcon(),
                asList(webFilter.servletNames()), findUrlPatterns(webFilter), asList(webFilter.dispatcherTypes()),
                webFilter.asyncSupported(), filter);
    }

    public FilterDetail(
            String description,
            String displayName,
            Map<String, String> initParams,
            String filterName,
            String smallIcon,
            String largeIcon,
            List<String> servletNames,
            List<String> urlPatterns,
            List<DispatcherType> dispatcherTypes,
            boolean asyncSupported,
            Filter filter
    ) {
        this.description = description;
        this.displayName = displayName;
        this.initParams = initParams;
        this.filterName = filterName;
        this.smallIcon = smallIcon;
        this.largeIcon = largeIcon;
        this.servletNames = servletNames;
        this.urlPatterns = urlPatterns;
        this.dispatcherTypes = dispatcherTypes;
        this.asyncSupported = asyncSupported;
        this.filter = filter;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Map<String, String> getInitParams() {
        return initParams;
    }

    public String getFilterName() {
        return filterName;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public String getLargeIcon() {
        return largeIcon;
    }

    public List<String> getServletNames() {
        return servletNames;
    }

    public List<String> getUrlPatterns() {
        return urlPatterns;
    }

    public List<DispatcherType> getDispatcherTypes() {
        return dispatcherTypes;
    }

    public boolean asyncSupported() {
        return asyncSupported;
    }

    public Filter getFilter() {
        return filter;
    }
}
