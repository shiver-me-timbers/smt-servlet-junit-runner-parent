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

/**
 * This matcher can be used to check that two {@link Iterable}s contain the same elements. The order of the elements is
 * ignored.
 *
 * @author Karl Bennett
 */
public class EqualAllMatcher<I extends Iterable> extends ContainsAllMatcher<I> {

    public static <I extends Iterable> EqualAllMatcher<I> equalAll(I expected) {
        return new EqualAllMatcher<>(expected);
    }

    private final I expected;

    public EqualAllMatcher(I expected) {
        super(expected);
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(I actual) {

        int expectedCount = 0;
        int actualCount = 0;

        for (Object e : expected) {
            expectedCount++;
        }

        for (Object e : actual) {
            actualCount++;
        }

        if (expectedCount != actualCount) {
            return false;
        }

        return super.matchesSafely(actual);
    }
}
