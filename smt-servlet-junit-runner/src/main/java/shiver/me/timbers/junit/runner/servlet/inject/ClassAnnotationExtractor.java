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

        if (null == type || Object.class.equals(type)) {
            log.warn("Annotation {} not found on {}", annotation, type);
            return null;
        }

        final A annotation = type.getAnnotation(this.annotation);

        if (null != annotation) {
            log.debug("Annotation {} found on {}", annotation, type);
            return annotation;
        }

        final A interfaceAnnotation = create(type.getInterfaces());

        if (null != interfaceAnnotation) {
            return interfaceAnnotation;
        }

        return create(type.getSuperclass());
    }

    private A create(Class<?>[] interfaces) {

        for (Class<?> type : interfaces) {

            final A annotation = create(type);

            if (null != annotation) {
                return annotation;
            }
        }

        return null;
    }
}
