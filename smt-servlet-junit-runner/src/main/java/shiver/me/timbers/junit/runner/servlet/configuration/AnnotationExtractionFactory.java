package shiver.me.timbers.junit.runner.servlet.configuration;

import java.lang.annotation.Annotation;

/**
 * @author Karl Bennett
 */
public class AnnotationExtractionFactory<A extends Annotation, T> {

    private final Class<A> annotationType;
    private final EmptyFactory<T> emptyFactory;
    private final AnnotationFactory<A, T> annotationFactory;

    public AnnotationExtractionFactory(
            Class<A> annotationType,
            EmptyFactory<T> emptyFactory,
            AnnotationFactory<A, T> annotationFactory
    ) {
        this.annotationType = annotationType;
        this.emptyFactory = emptyFactory;
        this.annotationFactory = annotationFactory;
    }

    public T create(Object target) {

        final Class<?> type = target.getClass();

        final A annotation = type.getAnnotation(annotationType);

        if (null == annotation) {
            return emptyFactory.create();
        }

        return annotationFactory.create(annotation);
    }
}
