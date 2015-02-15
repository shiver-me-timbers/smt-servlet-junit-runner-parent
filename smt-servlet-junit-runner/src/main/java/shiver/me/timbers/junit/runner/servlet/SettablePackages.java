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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class SettablePackages implements Packages {

    private static List<String> transform(String... packageStrings) {

        final List<String> details = new ArrayList<>(packageStrings.length);

        Collections.addAll(details, packageStrings);

        return details;
    }

    private final List<String> packages;

    public SettablePackages(String... packageStrings) {
        this(transform(packageStrings));
    }

    public SettablePackages(List<String> packages) {
        this.packages = packages;
    }

    public SettablePackages(Packages... packageses) {
        this.packages = new ArrayList<>();

        for (Packages packages : packageses) {
            add(packages);
        }
    }

    @Override
    public List<String> asList() {
        return packages;
    }

    public void add(Packages packages) {
        this.packages.addAll(packages.asList());
    }

    @Override
    public Iterator<String> iterator() {
        return packages.iterator();
    }

    @Override
    public String toString() {
        return format("%s { packages = '%s' }", getClass().getSimpleName(), packages);
    }
}
