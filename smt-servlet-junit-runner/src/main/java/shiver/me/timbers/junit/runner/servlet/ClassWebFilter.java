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
import javax.servlet.annotation.WebInitParam;

/**
 * @author Karl Bennett
 */
public class ClassWebFilter implements WebFilter {

    private final WebFilter webFilter;

    public ClassWebFilter(Class<? extends Filter> filter) {
        this.webFilter = WebFilterBuilder.create()
                .withFilterName(filter.getSimpleName())
                .withValue(new String[]{"/" + filter.getSimpleName()})
                .build();
    }

    @Override
    public boolean asyncSupported() {
        return webFilter.asyncSupported();
    }

    @Override
    public String description() {
        return webFilter.description();
    }

    @Override
    public DispatcherType[] dispatcherTypes() {
        return webFilter.dispatcherTypes();
    }

    @Override
    public String displayName() {
        return webFilter.displayName();
    }

    @Override
    public String filterName() {
        return webFilter.filterName();
    }

    @Override
    public WebInitParam[] initParams() {
        return webFilter.initParams();
    }

    @Override
    public String largeIcon() {
        return webFilter.largeIcon();
    }

    @Override
    public String[] servletNames() {
        return webFilter.servletNames();
    }

    @Override
    public String smallIcon() {
        return webFilter.smallIcon();
    }

    @Override
    public String[] urlPatterns() {
        return webFilter.urlPatterns();
    }

    @Override
    public String[] value() {
        return webFilter.value();
    }

    @Override
    public Class<WebFilter> annotationType() {
        return WebFilter.class;
    }
}
