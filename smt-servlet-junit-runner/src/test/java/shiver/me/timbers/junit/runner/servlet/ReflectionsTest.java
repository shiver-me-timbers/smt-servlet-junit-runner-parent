package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static shiver.me.timbers.junit.runner.servlet.Reflections.*;

/**
 * @author Karl Bennett
 */
public class ReflectionsTest {

    private static final String PUBLIC_METHOD_NAME = "publicMethod";
    private static final String PRIVATE_METHOD_NAME = "privateMethod";
    private static final String NO_ARG_METHOD_RESULT = "no-arg-method-result";

    private static final Method PUBLIC_METHOD = method(TestDefault.class, PUBLIC_METHOD_NAME);
    private static final Method PUBLIC_METHOD_WITH_ARG = method(TestDefault.class, PUBLIC_METHOD_NAME, String.class);
    private static final Method PRIVATE_METHOD = method(TestDefault.class, PRIVATE_METHOD_NAME);
    private static final Method PRIVATE_METHOD_WITH_ARG = method(TestDefault.class, PRIVATE_METHOD_NAME, String.class);

    private static Method method(Class<?> type, String methodName, Class... args) {
        try {
            return type.getDeclaredMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void Can_create_reflections() {

        new Reflections();
    }

    @Test
    public void Can_instantiate_class_with_default_constructor() {

        // When
        final TestDefault actual = instantiate(TestDefault.class);

        // Then
        assertNotNull(actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_instantiate_class_with_private_default_constructor() {

        // When
        instantiate(TestPrivateDefault.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_instantiate_class_with_non_default_constructor() {

        // When
        instantiate(TestNoDefault.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_instantiate_abstract_class() {

        // When
        instantiate(TestAbstract.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_instantiate_interface() {

        // When
        instantiate(TestInterface.class);
    }

    @Test
    public void Can_instantiate_class_with_non_default_constructor() {

        // When
        final TestNoDefault actual = instantiate(TestNoDefault.class, new Object());

        // Then
        assertNotNull(actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_instantiate_abstract_class_with_non_default_constructor() {

        // When
        instantiate(TestAbstract.class, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_instantiate_interface_with_non_default_constructor() {

        // When
        instantiate(TestInterface.class, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_instantiate_class_with_wrong_number_of_constructor_arguments() {

        // When
        instantiate(TestNoDefault.class, new Object(), new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_instantiate_class_with_private_non_default_constructor() {

        // When
        instantiate(TestPrivateNoDefault.class, new Object());
    }

    @Test
    public void Can_get_a_method_with_no_arguments() throws InvocationTargetException, IllegalAccessException {

        // Given
        final TestDefault object = new TestDefault();

        // When
        final Method actual = getMethod(object, PUBLIC_METHOD_NAME);

        // Then
        assertNotNull(actual);
        assertEquals(NO_ARG_METHOD_RESULT, actual.invoke(object));
    }

    @Test
    public void Can_get_a_method_with_arguments() throws InvocationTargetException, IllegalAccessException {

        // Given
        final TestDefault object = new TestDefault();
        final String expected = "test string";

        // When
        final Method actual = getMethod(object, PUBLIC_METHOD_NAME, expected);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual.invoke(object, expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_get_a_private_method_with_no_arguments() throws InvocationTargetException, IllegalAccessException {

        // Given
        final TestDefault object = new TestDefault();

        // When
        getMethod(object, PRIVATE_METHOD_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_get_a_private_method_with_arguments() throws InvocationTargetException, IllegalAccessException {

        // Given
        final TestDefault object = new TestDefault();

        // When
        getMethod(object, PRIVATE_METHOD_NAME, "test string");
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_get_a_method_that_does_not_exist() throws InvocationTargetException, IllegalAccessException {

        // Given
        final TestDefault object = new TestDefault();

        // When
        getMethod(object, "doesNotExist");
    }

    @Test
    public void Can_call_a_method_with_no_arguments() throws InvocationTargetException, IllegalAccessException {

        // When
        final String actual = callMethod(new TestDefault(), PUBLIC_METHOD);

        // Then
        assertEquals(NO_ARG_METHOD_RESULT, actual);
    }

    @Test
    public void Can_call_a_method_with_arguments() throws InvocationTargetException, IllegalAccessException {

        // Given
        final String expected = "test string";

        // When
        final String actual = callMethod(new TestDefault(), PUBLIC_METHOD_WITH_ARG, expected);

        // Then
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_call_a_private_method_with_no_arguments() throws InvocationTargetException, IllegalAccessException {

        // When
        callMethod(new TestDefault(), PRIVATE_METHOD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_call_a_private_method_with_arguments() throws InvocationTargetException, IllegalAccessException {

        // When
        callMethod(new TestDefault(), PRIVATE_METHOD_WITH_ARG, "test string");
    }

    @Test(expected = IllegalArgumentException.class)
    public void Cannot_call_a_method_with_wrong_arguments() throws InvocationTargetException, IllegalAccessException {

        // When
        callMethod(new TestDefault(), PUBLIC_METHOD_WITH_ARG);
    }

    public static class TestDefault {

        public String publicMethod() {
            return NO_ARG_METHOD_RESULT;
        }

        public String publicMethod(String arg) {
            return arg;
        }

        private String privateMethod() {
            return NO_ARG_METHOD_RESULT;
        }

        private String privateMethod(String arg) {
            return arg;
        }
    }

    public static class TestPrivateDefault {
        private TestPrivateDefault() {
        }
    }

    public static class TestNoDefault {
        public TestNoDefault(Object arg) {
        }
    }

    public static class TestPrivateNoDefault {
        private TestPrivateNoDefault(Object arg) {
        }
    }

    private static abstract class TestAbstract {
    }

    private static interface TestInterface {
    }
}
