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

package shiver.me.timbers.junit.runner.servlet.inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

/**
 * @author Karl Bennett
 */
public class ClassAnnotationExtractor<A extends Annotation> implements AnnotationExtractor<A> {

    private final Logger log = LoggerFactory.getLogger(ClassAnnotationExtractor.class);

    private final Class<A> annotation;

    public ClassAnnotationExtractor(Class<A> annotation) {
        this.annotation = annotation;

        log.debug("Constructed");
    }

    @Override
    public A create(Class<?> type) {
        return create(type, type);
    }

    public A create(Class<?> origin, Class<?> type) {

        if (null == type || Object.class.equals(type)) {
            log.warn("Annotation {} not found on {}", annotation, origin);
            return null;
        }

        final A annotation = type.getAnnotation(this.annotation);

        if (null != annotation) {
            log.debug("Annotation {} found on {}. Started searching at {}", annotation, type, origin);
            return annotation;
        }

        final A interfaceAnnotation = create(origin, type.getInterfaces());

        if (null != interfaceAnnotation) {
            return interfaceAnnotation;
        }

        return create(origin, type.getSuperclass());
    }

    private A create(Class<?> origin, Class<?>[] interfaces) {

        for (Class<?> type : interfaces) {

            final A annotation = create(origin, type);

            if (null != annotation) {
                return annotation;
            }
        }

        return null;
    }
}
