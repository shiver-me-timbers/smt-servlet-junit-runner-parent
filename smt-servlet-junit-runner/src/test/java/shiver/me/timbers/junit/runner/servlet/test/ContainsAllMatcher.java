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

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.LinkedList;
import java.util.List;

/**
 * This matcher can be used to check that two {@link Iterable}s contain the same elements. The order of the elements is
 * ignored.
 *
 * @author Karl Bennett
 */
public class ContainsAllMatcher<I extends Iterable> extends TypeSafeMatcher<I> {

    private final I expected;

    public ContainsAllMatcher(I expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(I actual) {

        final List<Object> processingActual = toList(actual);

        for (Object exp : expected) {

            if (!matchFound(processingActual, exp)) {
                return false;
            }

            processingActual.remove(exp);
        }

        return true;
    }

    private boolean matchFound(List<Object> actual, Object exp) {

        for (Object act : actual) {
            if (exp.equals(act)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }

    private static List<Object> toList(Iterable expected) {

        final List<Object> list = new LinkedList<>();

        for (Object element : expected) {
            list.add(element);
        }

        return list;
    }
}
