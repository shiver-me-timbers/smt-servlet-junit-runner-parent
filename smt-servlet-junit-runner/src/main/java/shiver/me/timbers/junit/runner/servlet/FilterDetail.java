/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.servlet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static shiver.me.timbers.junit.runner.servlet.Reflections.instantiate;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.buildAnnotation;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.findUrlPatterns;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.transform;

/**
 * @author Karl Bennett
 */
public class FilterDetail {

    private static String findFilterName(Class<? extends Filter> filter, WebFilter webFilter) {

        final String name = webFilter.filterName();

        if (name.isEmpty()) {
            return filter.getSimpleName();
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
    private final Class<? extends Filter> filter;

    public FilterDetail(Class<? extends Filter> filter) {
        this(filter, buildAnnotation(filter, WebFilter.class, ClassWebFilter.class));
    }

    public FilterDetail(Class<? extends Filter> filter, WebFilter webFilter) {
        this(
                webFilter.description(),
                webFilter.displayName(),
                transform(webFilter.initParams()),
                findFilterName(filter, webFilter),
                webFilter.smallIcon(),
                webFilter.largeIcon(),
                asList(webFilter.servletNames()),
                findUrlPatterns(webFilter),
                asList(webFilter.dispatcherTypes()),
                webFilter.asyncSupported(),
                filter
        );
    }

    public FilterDetail(FilterDetail filterDetail) {
        this(
                filterDetail.getDescription(),
                filterDetail.getDisplayName(),
                filterDetail.getInitParams(),
                filterDetail.getFilterName(),
                filterDetail.getSmallIcon(),
                filterDetail.getLargeIcon(),
                filterDetail.getServletNames(),
                filterDetail.getUrlPatterns(),
                filterDetail.getDispatcherTypes(),
                filterDetail.asyncSupported(),
                filterDetail.getFilter()
        );
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
            Class<? extends Filter> filter
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
        return null == initParams ? null : new HashMap<>(initParams);
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
        return null == servletNames ? null : new ArrayList<>(servletNames);
    }

    public List<String> getUrlPatterns() {
        return null == urlPatterns ? null : new ArrayList<>(urlPatterns);
    }

    public List<DispatcherType> getDispatcherTypes() {
        return null == dispatcherTypes ? null : new ArrayList<>(dispatcherTypes);
    }

    public boolean asyncSupported() {
        return asyncSupported;
    }

    public Class<? extends Filter> getFilter() {
        return filter;
    }

    public Filter getFilterInstance() {
        return instantiate(filter);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final FilterDetail that = (FilterDetail) o;

        if (asyncSupported != that.asyncSupported) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (dispatcherTypes != null ? !dispatcherTypes.equals(that.dispatcherTypes) : that.dispatcherTypes != null) {
            return false;
        }
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) {
            return false;
        }
        if (filter != null ? !filter.equals(that.filter) : that.filter != null) {
            return false;
        }
        if (filterName != null ? !filterName.equals(that.filterName) : that.filterName != null) {
            return false;
        }
        if (initParams != null ? !initParams.equals(that.initParams) : that.initParams != null) {
            return false;
        }
        if (largeIcon != null ? !largeIcon.equals(that.largeIcon) : that.largeIcon != null) {
            return false;
        }
        if (servletNames != null ? !servletNames.equals(that.servletNames) : that.servletNames != null) {
            return false;
        }
        if (smallIcon != null ? !smallIcon.equals(that.smallIcon) : that.smallIcon != null) {
            return false;
        }
        if (urlPatterns != null ? !urlPatterns.equals(that.urlPatterns) : that.urlPatterns != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (initParams != null ? initParams.hashCode() : 0);
        result = 31 * result + (filterName != null ? filterName.hashCode() : 0);
        result = 31 * result + (smallIcon != null ? smallIcon.hashCode() : 0);
        result = 31 * result + (largeIcon != null ? largeIcon.hashCode() : 0);
        result = 31 * result + (servletNames != null ? servletNames.hashCode() : 0);
        result = 31 * result + (urlPatterns != null ? urlPatterns.hashCode() : 0);
        result = 31 * result + (dispatcherTypes != null ? dispatcherTypes.hashCode() : 0);
        result = 31 * result + (asyncSupported ? 1 : 0);
        result = 31 * result + (filter != null ? filter.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return format(
                "FilterDetail {\n" +
                        "description='%s',\n" +
                        "displayName='%s',\n" +
                        "initParams=%s,\n" +
                        "filterName='%s',\n" +
                        "smallIcon='%s',\n" +
                        "largeIcon='%s',\n" +
                        "servletNames=%s,\n" +
                        "urlPatterns=%s,\n" +
                        "dispatcherTypes=%s,\n" +
                        "asyncSupported=%s,\n" +
                        "filter=%s,\n" +
                        "}",
                description,
                displayName,
                initParams,
                filterName,
                smallIcon,
                largeIcon,
                servletNames,
                urlPatterns,
                dispatcherTypes,
                asyncSupported,
                filter
        );
    }
}
