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

package shiver.me.timbers.junit.runner.servlet.configuration.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiver.me.timbers.junit.runner.servlet.FilterDetail;
import shiver.me.timbers.junit.runner.servlet.Filters;
import shiver.me.timbers.junit.runner.servlet.SettableFilters;
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.annotation.FilterConfiguration;
import shiver.me.timbers.junit.runner.servlet.configuration.AnnotationFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class ConfiguredFiltersAnnotationFactory implements AnnotationFactory<ContainerConfiguration, Filters> {

    private final Logger log = LoggerFactory.getLogger(ConfiguredFiltersAnnotationFactory.class);

    @Override
    public Filters create(ContainerConfiguration annotation) {

        final FilterConfiguration[] configurations = annotation.filterConfigurations();

        log.debug("Adding configured filters");
        final List<FilterDetail> filterDetails = new ArrayList<>();
        for (FilterConfiguration configuration : configurations) {
            log.debug("Adding {}", configuration);
            filterDetails.add(new FilterDetail(configuration.servlet(), configuration.configuration()));
        }
        log.debug("Added {}", filterDetails);
        return new SettableFilters(filterDetails);
    }
}
