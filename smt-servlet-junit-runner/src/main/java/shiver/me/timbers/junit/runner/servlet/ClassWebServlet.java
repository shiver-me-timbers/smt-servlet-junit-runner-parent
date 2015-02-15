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

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.lang.annotation.Annotation;

/**
 * @author Karl Bennett
 */
public class ClassWebServlet implements WebServlet {

    private final WebServlet webServlet;

    public ClassWebServlet(Class<? extends Servlet> servlet) {
        this.webServlet = WebServletBuilder.create()
                .withName(servlet.getSimpleName())
                .withValue(new String[]{"/" + servlet.getSimpleName()})
                .build();
    }

    @Override
    public boolean asyncSupported() {
        return webServlet.asyncSupported();
    }

    @Override
    public String description() {
        return webServlet.description();
    }

    @Override
    public String displayName() {
        return webServlet.displayName();
    }

    @Override
    public WebInitParam[] initParams() {
        return webServlet.initParams();
    }

    @Override
    public String largeIcon() {
        return webServlet.largeIcon();
    }

    @Override
    public int loadOnStartup() {
        return webServlet.loadOnStartup();
    }

    @Override
    public String name() {
        return webServlet.name();
    }

    @Override
    public String smallIcon() {
        return webServlet.smallIcon();
    }

    @Override
    public String[] urlPatterns() {
        return webServlet.urlPatterns();
    }

    @Override
    public String[] value() {
        return webServlet.value();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return webServlet.annotationType();
    }
}
