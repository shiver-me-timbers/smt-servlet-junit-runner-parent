package shiver.me.timbers.junit.runner.servlet.configuration;

import shiver.me.timbers.junit.runner.servlet.EmptyFactory;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

import java.lang.annotation.Annotation;

/**
 * @author Karl Bennett
 */
public class AnnotationExtractionFactory<A extends Annotation, T> {

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
    }

    public T create(Object target) {

        final Class<?> type = target.getClass();

        final A annotation = annotationExtractor.create(type);

        if (null == annotation) {
            return emptyFactory.create();
        }

        return annotationFactory.create(annotation);
    }
}
