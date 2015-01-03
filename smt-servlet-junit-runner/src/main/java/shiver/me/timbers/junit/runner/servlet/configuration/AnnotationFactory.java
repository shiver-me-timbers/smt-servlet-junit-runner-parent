package shiver.me.timbers.junit.runner.servlet.configuration;

import shiver.me.timbers.junit.runner.servlet.Factory;

import java.lang.annotation.Annotation;

/**
 * This factory is used when an annotation will be used to create an instance.
 *
 * @author Karl Bennett
 */
public interface AnnotationFactory<A extends Annotation, T> extends Factory<A, T> {
}
