package shiver.me.timbers.junit.runner.servlet.inject;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClassAnnotationExtractorTest {

    private static final String CLASS_DIRECT = "class direct annotation";
    private static final String INTERFACE_DIRECT = "interface direct annotation";
    private static final String CLASS_INHERITED = "class inherited annotation";
    private static final String INTERFACE_INHERITED = "interface inherited annotation";
    private static final String IMPLEMENTED_INHERITED = "implementation inherited annotation";

    @Test
    public void An_annotation_can_be_extracted_from_a_class() throws Exception {

        // When
        final TestAnnotation annotation = new ClassAnnotationExtractor<>(TestAnnotation.class)
                .create(AnnotatedClass.class);

        // Then
        assertEquals(CLASS_DIRECT, annotation.actual());
    }

    @Test
    public void An_annotation_can_be_extracted_from_an_inherited_class() throws Exception {

        // When
        final TestAnnotation annotation = new ClassAnnotationExtractor<>(TestAnnotation.class)
                .create(InheritedAnnotatedClass.class);

        // Then
        assertEquals(CLASS_INHERITED, annotation.actual());
    }

    @Test
    public void An_annotation_cannot_be_extracted_from_a_class_with_no_annotation() throws Exception {

        // When
        final TestAnnotation actual = new ClassAnnotationExtractor<>(TestAnnotation.class)
                .create(NoAnnotationClass.class);

        // Then
        assertNull(actual);
    }

    @Test
    public void An_annotation_can_be_extracted_from_an_interface() throws Exception {

        // When
        final TestAnnotation annotation = new ClassAnnotationExtractor<>(TestAnnotation.class)
                .create(AnnotatedInterface.class);

        // Then
        assertEquals(INTERFACE_DIRECT, annotation.actual());
    }

    @Test
    public void An_annotation_can_be_extracted_from_an_inherited_interface() throws Exception {

        // When
        final TestAnnotation annotation = new ClassAnnotationExtractor<>(TestAnnotation.class)
                .create(InheritedAnnotatedInterface.class);

        // Then
        assertEquals(INTERFACE_INHERITED, annotation.actual());
    }

    @Test
    public void An_annotation_cannot_be_extracted_from_an_interface_with_no_annotation() throws Exception {

        // When
        final TestAnnotation actual = new ClassAnnotationExtractor<>(TestAnnotation.class)
                .create(NoAnnotationInterface.class);

        // Then
        assertNull(actual);
    }

    @Test
    public void An_annotation_can_be_extracted_from_an_implemented_interface() throws Exception {

        // When
        final TestAnnotation annotation = new ClassAnnotationExtractor<>(TestAnnotation.class)
                .create(InheritedImplementedAnnotationClass.class);

        // Then
        assertEquals(IMPLEMENTED_INHERITED, annotation.actual());
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    private static @interface TestAnnotation {
        String actual();
    }

    @TestAnnotation(actual = CLASS_DIRECT)
    private static class AnnotatedClass {
    }

    @TestAnnotation(actual = CLASS_INHERITED)
    private static class SuperAnnotatedClass {
    }

    private static class InheritedAnnotatedClass extends SuperAnnotatedClass {
    }

    private static class NoAnnotationClass {
    }

    @TestAnnotation(actual = INTERFACE_DIRECT)
    private static interface AnnotatedInterface {
    }

    @TestAnnotation(actual = INTERFACE_INHERITED)
    private static interface SuperAnnotatedInterface {
    }

    private static interface InheritedAnnotatedInterface extends SuperAnnotatedInterface {
    }

    private static interface NoAnnotationInterface {
    }

    @TestAnnotation(actual = IMPLEMENTED_INHERITED)
    private static interface BranchSuperAnnotatedInterface {
    }

    private static interface BranchInheritedAnnotatedInterface extends BranchSuperAnnotatedInterface {
    }

    private static class ImplementedAnnotationClass implements BranchInheritedAnnotatedInterface {
    }

    private static class InheritedImplementedAnnotationClass extends ImplementedAnnotationClass {
    }
}
