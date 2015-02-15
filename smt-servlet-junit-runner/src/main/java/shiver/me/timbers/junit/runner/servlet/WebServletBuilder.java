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

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author Karl Bennett
 */
public class WebServletBuilder {

    public static WebServletBuilder create() {
        return new WebServletBuilder();
    }

    private String name = "";
    private String[] value = new String[0];
    private String[] urlPatterns = new String[0];
    private int loadOnStartup = -1;
    private WebInitParam[] initParams = new WebInitParam[0];
    private boolean asyncSupported = false;
    private String smallIcon = "";
    private String largeIcon = "";
    private String description = "";
    private String displayName = "";
    private Class<WebServlet> annotationType = WebServlet.class;

    private WebServletBuilder() {
    }

    public WebServletBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public WebServletBuilder withValue(String... value) {
        this.value = value;
        return this;
    }

    public WebServletBuilder withUrlPatterns(String... urlPatterns) {
        this.urlPatterns = urlPatterns;
        return this;
    }

    public WebServletBuilder withLoadOnStartup(int loadOnStartup) {
        this.loadOnStartup = loadOnStartup;
        return this;
    }

    public WebServletBuilder withInitParams(WebInitParam... initParams) {
        this.initParams = initParams;
        return this;
    }

    public WebServletBuilder withAsyncSupported(boolean asyncSupported) {
        this.asyncSupported = asyncSupported;
        return this;
    }

    public WebServletBuilder withLargeIcon(String largeIcon) {
        this.largeIcon = largeIcon;
        return this;
    }

    public WebServletBuilder withSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
        return this;
    }

    public WebServletBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public WebServletBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public WebServlet build() {
        return new BuiltWebServlet();
    }

    private class BuiltWebServlet implements WebServlet {

        @Override
        public String name() {
            return name;
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
        public int loadOnStartup() {
            return loadOnStartup;
        }

        @Override
        public WebInitParam[] initParams() {
            return initParams;
        }

        @Override
        public boolean asyncSupported() {
            return asyncSupported;
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
        public String description() {
            return description;
        }

        @Override
        public String displayName() {
            return displayName;
        }

        @Override
        public Class<WebServlet> annotationType() {
            return annotationType;
        }
    }
}
