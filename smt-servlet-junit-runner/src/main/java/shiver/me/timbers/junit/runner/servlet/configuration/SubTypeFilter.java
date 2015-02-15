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

package shiver.me.timbers.junit.runner.servlet.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SubTypeFilter<T> implements Factory<List<Class>, List<Class<? extends T>>> {

    private final Logger log = LoggerFactory.getLogger(SubTypeFilter.class);

    private final Class<T> type;

    public SubTypeFilter(Class<T> type) {
        this.type = type;

        log.debug("Constructed.");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Class<? extends T>> create(List<Class> classes) {

        final List<Class<? extends T>> subTypes = new ArrayList<>();

        log.debug("Filtering out subtypes of {}", type);
        for (Class type : classes) {
            if (this.type.isAssignableFrom(type)) {
                log.debug("Subtype {} found for {}", type, this.type);
                subTypes.add(type);
            }
        }
        log.debug("Filter has left {}", subTypes);
        return subTypes;
    }
}
