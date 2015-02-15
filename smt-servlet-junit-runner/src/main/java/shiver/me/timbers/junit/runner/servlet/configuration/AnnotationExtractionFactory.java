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
            log.debug("No annotation found on {}", target);
            return emptyFactory.create();
        }
        log.debug("Annotation found on {}. Creating new instance", target);
        return annotationFactory.create(annotation);
    }
}
