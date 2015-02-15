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
import shiver.me.timbers.junit.runner.servlet.EmptyFactory;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

import java.lang.annotation.Annotation;

/**
 * @author Karl Bennett
 */
public class AnnotationExtractionFactory<A extends Annotation, T> {

    private final Logger log = LoggerFactory.getLogger(AnnotationExtractionFactory.class);

    private final AnnotationExtractor<A> annotationExtractor;
    private final EmptyFactory<T> emptyFactory;
    private final AnnotationFactory<A, T> annotationFactory;

    public AnnotationExtractionFactory(
            AnnotationExtractor<A> annotationExtractor,
            EmptyFactory<T> emptyFactory,
            AnnotationFactory<A, T> annotationFactory
    ) {
        this.annotationExtractor = annotationExtractor;
        this.emptyFactory = emptyFactory;
        this.annotationFactory = annotationFactory;

        log.debug("Constructed");
    }

    public T create(Object target) {

        final Class<?> type = target.getClass();

        final A annotation = annotationExtractor.create(type);

        if (null == annotation) {
            log.warn("No annotation found on {}", target);
            return emptyFactory.create();
        }
        log.debug("Annotation found on {}. Creating new instance", target);
        return annotationFactory.create(annotation);
    }
}
