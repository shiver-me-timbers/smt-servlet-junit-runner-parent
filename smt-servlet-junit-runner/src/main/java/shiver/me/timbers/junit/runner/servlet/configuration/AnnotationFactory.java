package shiver.me.timbers.junit.runner.servlet.configuration;

import java.lang.annotation.Annotation;

/**
 * This factory is used when an annotation will be used to create an instance.
 *
 * @author Karl Bennett
 */
public interface AnnotationFactory<A extends Annotation, T> {
    T create(A annotation);
}
