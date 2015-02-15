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

package shiver.me.timbers.junit.runner.servlet.test.two;

import shiver.me.timbers.junit.runner.servlet.test.BaseFilter;

import javax.servlet.annotation.WebFilter;

import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.PACKAGE_FILTER_DETAIL_THREE_NAME;
import static shiver.me.timbers.junit.runner.servlet.test.FilterConstants.PACKAGE_FILTER_DETAIL_THREE_PATH;

@WebFilter(filterName = PACKAGE_FILTER_DETAIL_THREE_NAME, value = PACKAGE_FILTER_DETAIL_THREE_PATH)
public class PackageFilterThree extends BaseFilter {
}
