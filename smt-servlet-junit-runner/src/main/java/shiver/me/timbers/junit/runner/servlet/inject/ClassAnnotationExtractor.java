package shiver.me.timbers.junit.runner.servlet.inject;

import java.lang.annotation.Annotation;

/**
 * @author Karl Bennett
 */
public class ClassAnnotationExtractor<A extends Annotation> implements AnnotationExtractor<A> {

    private final Class<A> annotation;

    public ClassAnnotationExtractor(Class<A> annotation) {
        this.annotation = annotation;
    }

    @Override
    public A create(Class<?> type) {

        if (null == type || Object.class.equals(type)) {
            return null;
        }

        final A annotation = type.getAnnotation(this.annotation);

        if (null != annotation) {
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
