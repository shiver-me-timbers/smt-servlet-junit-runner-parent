package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;
import shiver.me.timbers.junit.runner.servlet.EmptyFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class AnnotationExtractionFactoryTest {

    @Test
    public void Nothing_is_created_if_no_configuration_supplied() {

        @SuppressWarnings("unchecked")
        final EmptyFactory<Object> emptyFactory = mock(EmptyFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<TestAnnotation, Object> annotationFactory = mock(AnnotationFactory.class);
        final Object expected = new Object();

        // Given
        when(emptyFactory.create()).thenReturn(expected);

        class TestClass {
        }

        // When
        final Object actual = new AnnotationExtractionFactory<>(TestAnnotation.class, emptyFactory, annotationFactory)
                .create(new TestClass());

        // Then
        assertEquals(expected, actual);
        verifyZeroInteractions(annotationFactory);
    }

    @Test
    public void Something_is_created_if_configuration_supplied() {

        @SuppressWarnings("unchecked")
        final EmptyFactory<Object> emptyFactory = mock(EmptyFactory.class);
        @SuppressWarnings("unchecked")
        final AnnotationFactory<TestAnnotation, Object> annotationFactory = mock(AnnotationFactory.class);
        final Object expected = new Object();

        // Given
        when(annotationFactory.create(any(TestAnnotation.class))).thenReturn(expected);

        @TestAnnotation
        class TestClass {
        }

        // When
        final Object actual = new AnnotationExtractionFactory<>(TestAnnotation.class, emptyFactory, annotationFactory)
                .create(new TestClass());

        // Then
        assertEquals(expected, actual);
        verifyZeroInteractions(emptyFactory);
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    public static @interface TestAnnotation {
    }
}
