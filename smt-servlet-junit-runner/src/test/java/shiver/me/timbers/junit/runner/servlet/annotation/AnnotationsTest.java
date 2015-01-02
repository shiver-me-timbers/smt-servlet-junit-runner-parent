package shiver.me.timbers.junit.runner.servlet.annotation;

import org.junit.Test;

import javax.servlet.annotation.WebInitParam;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.buildAnnotation;
import static shiver.me.timbers.junit.runner.servlet.annotation.Annotations.transform;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.INIT;
import static shiver.me.timbers.junit.runner.servlet.test.Constants.PARAM;

public class AnnotationsTest {

    private static final String ANNOTATION_VALUE = "annotation-value";

    @Test
    public void Can_create_annotations() {

        new Annotations();
    }

    @Test
    public void Can_build_the_annotation_from_an_annotated_class() {

        // Given
        @TestAnnotation(ANNOTATION_VALUE)
        class AnnotatedClass {
        }

        // When
        final TestAnnotation actual = buildAnnotation(AnnotatedClass.class, TestAnnotation.class,
                TestAnnotationWrapper.class);

        // Then
        assertEquals(ANNOTATION_VALUE, actual.value());
    }

    @Test
    public void Can_build_the_annotation_from_a_non_annotated_class() {

        // Given
        class AnnotatedClass {
        }

        // When
        final TestAnnotation actual = buildAnnotation(AnnotatedClass.class, TestAnnotation.class,
                TestAnnotationWrapper.class);

        // Then
        assertEquals(AnnotatedClass.class.getSimpleName(), actual.value());
    }

    @Test
    public void Can_transform_init_params() {

        // Given
        @WebInitParam(name = INIT, value = PARAM)
        class AnnotatedClass {
        }

        // When
        final Map<String, String> actual = transform(AnnotatedClass.class.getAnnotation(WebInitParam.class));

        // Then
        assertEquals(singletonMap(INIT, PARAM), actual);
    }

    @Target({FIELD, TYPE})
    @Retention(RUNTIME)
    public static @interface TestAnnotation {

        String value();
    }

    public static class TestAnnotationWrapper implements TestAnnotation {

        private final Class type;

        public TestAnnotationWrapper(Class type) {
            this.type = type;
        }

        @Override
        public String value() {
            return type.getSimpleName();
        }

        @Override
        public Class<TestAnnotation> annotationType() {
            return TestAnnotation.class;
        }
    }
}
