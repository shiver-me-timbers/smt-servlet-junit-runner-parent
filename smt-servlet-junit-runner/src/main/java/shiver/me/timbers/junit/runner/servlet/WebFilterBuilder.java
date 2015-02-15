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
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author Karl Bennett
 */
public class WebFilterBuilder {

    public static WebFilterBuilder create() {
        return new WebFilterBuilder();
    }

    private String description = "";
    private String displayName = "";
    private WebInitParam[] initParams = new WebInitParam[0];
    private String filterName = "";
    private String smallIcon = "";
    private String largeIcon = "";
    private String[] servletNames = new String[0];
    private String[] value = new String[0];
    private String[] urlPatterns = new String[0];
    private DispatcherType[] dispatcherTypes = {DispatcherType.REQUEST};
    private boolean asyncSupported = false;
    private Class<WebFilter> annotationType = WebFilter.class;

    private WebFilterBuilder() {
    }

    public WebFilterBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public WebFilterBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public WebFilterBuilder withInitParams(WebInitParam... initParams) {
        this.initParams = initParams;
        return this;
    }

    public WebFilterBuilder withFilterName(String filterName) {
        this.filterName = filterName;
        return this;
    }

    public WebFilterBuilder withSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
        return this;
    }

    public WebFilterBuilder withLargeIcon(String largeIcon) {
        this.largeIcon = largeIcon;
        return this;
    }

    public WebFilterBuilder withServletNames(String... servletNames) {
        this.servletNames = servletNames;
        return this;
    }

    public WebFilterBuilder withValue(String... value) {
        this.value = value;
        return this;
    }

    public WebFilterBuilder withUrlPatterns(String... urlPatterns) {
        this.urlPatterns = urlPatterns;
        return this;
    }

    public WebFilterBuilder withDispatcherTypes(DispatcherType... dispatcherTypes) {
        this.dispatcherTypes = dispatcherTypes;
        return this;
    }

    public WebFilterBuilder withAsyncSupported(boolean asyncSupported) {
        this.asyncSupported = asyncSupported;
        return this;
    }

    public WebFilter build() {
        return new BuiltWebFilter();
    }

    private class BuiltWebFilter implements WebFilter {

        @Override
        public String description() {
            return description;
        }

        @Override
        public String displayName() {
            return displayName;
        }

        @Override
        public WebInitParam[] initParams() {
            return initParams;
        }

        @Override
        public String filterName() {
            return filterName;
        }

        @Override
        public String smallIcon() {
            return smallIcon;
        }

        @Override
        public String largeIcon() {
            return largeIcon;
        }

        @Override
        public String[] servletNames() {
            return servletNames;
        }

        @Override
        public String[] value() {
            return value;
        }

        @Override
        public String[] urlPatterns() {
            return urlPatterns;
        }

        @Override
        public DispatcherType[] dispatcherTypes() {
            return dispatcherTypes;
        }

        @Override
        public boolean asyncSupported() {
            return asyncSupported;
        }

        @Override
        public Class<WebFilter> annotationType() {
            return annotationType;
        }
    }
}
