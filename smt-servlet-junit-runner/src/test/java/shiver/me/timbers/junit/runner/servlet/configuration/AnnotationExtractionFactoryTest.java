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

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.EmptyFactory;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class AnnotationExtractionFactoryTest {

    @Test
    public void Nothing_is_created_if_no_configuration_supplied() {

        class TestClass {
        }

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<TestAnnotation> annotationExtractor = mock(AnnotationExtractor.class);
        @SuppressWarnings("unchecked")
        final EmptyFactory<Object> emptyFactory = mock(EmptyFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<TestAnnotation, Object> annotationFactory = mock(AnnotationFactory.class);

        final Object expected = new Object();

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(null);
        when(emptyFactory.create()).thenReturn(expected);

        // When
        final Object actual = new AnnotationExtractionFactory<>(
                annotationExtractor,
                emptyFactory,
                annotationFactory
        ).create(new TestClass());

        // Then
        assertEquals(expected, actual);
        verifyZeroInteractions(annotationFactory);
    }

    @Test
    public void Something_is_created_if_configuration_supplied() {

        @TestAnnotation
        class TestClass {
        }

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<TestAnnotation> annotationExtractor = mock(AnnotationExtractor.class);
        @SuppressWarnings("unchecked")
        final EmptyFactory<Object> emptyFactory = mock(EmptyFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<TestAnnotation, Object> annotationFactory = mock(AnnotationFactory.class);

        final TestAnnotation annotation = mock(TestAnnotation.class);

        final Object expected = new Object();

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(annotation);
        when(annotationFactory.create(annotation)).thenReturn(expected);

        // When
        final Object actual = new AnnotationExtractionFactory<>(
                annotationExtractor,
                emptyFactory,
                annotationFactory
        ).create(new TestClass());

        // Then
        assertEquals(expected, actual);
        verifyZeroInteractions(emptyFactory);
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    public static @interface TestAnnotation {
    }
}
