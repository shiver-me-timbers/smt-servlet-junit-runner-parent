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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SettableServlets implements Servlets {

    @SafeVarargs
    private static List<ServletDetail> transform(Class<? extends Servlet>... servlets) {

        final List<ServletDetail> details = new ArrayList<>(servlets.length);

        for (Class<? extends Servlet> servlet : servlets) {
            details.add(new ServletDetail(servlet));
        }

        return details;
    }

    private final List<ServletDetail> servlets;

    @SafeVarargs
    public SettableServlets(Class<? extends Servlet>... servlets) {
        this(transform(servlets));
    }

    public SettableServlets(List<ServletDetail> servlets) {
        this.servlets = servlets;
    }

    public SettableServlets(Servlets... servletses) {
        this.servlets = new ArrayList<>();

        for (Servlets servlets : servletses) {
            add(servlets);
        }
    }

    @Override
    public List<ServletDetail> asList() {
        return servlets;
    }

    @Override
    public void add(Servlets servlets) {
        this.servlets.addAll(servlets.asList());
    }

    @Override
    public Iterator<ServletDetail> iterator() {
        return servlets.iterator();
    }
}
