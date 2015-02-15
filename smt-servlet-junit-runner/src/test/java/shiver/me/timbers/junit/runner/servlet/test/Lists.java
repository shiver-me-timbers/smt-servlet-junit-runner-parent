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

package shiver.me.timbers.junit.runner.servlet.test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class Lists {

    @SafeVarargs
    public static <T> List<T> toList(T... elements) {

        final List<T> aggregate = new ArrayList<>();

        addAll(aggregate, elements);

        return aggregate;
    }

    @SafeVarargs
    public static <T> List<T> toList(List<T> list, T... elements) {

        final List<T> aggregate = new ArrayList<>();

        addAll(aggregate, elements);

        return toList(list, aggregate);
    }

    @SafeVarargs
    public static <T> List<T> toList(List<T>... lists) {

        final List<T> list = new ArrayList<>();

        for (List<T> part : lists) {
            list.addAll(part);
        }

        return list;
    }
}
