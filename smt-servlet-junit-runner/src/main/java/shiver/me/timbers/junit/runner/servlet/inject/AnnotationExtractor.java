package shiver.me.timbers.junit.runner.servlet.inject;

import shiver.me.timbers.junit.runner.servlet.Factory;

import java.lang.annotation.Annotation;

/**
 * @author Karl Bennett
 */
public interface AnnotationExtractor<A extends Annotation> extends Factory<Class<?>, A> {
}
