package shiver.me.timbers.junit.runner.servlet.config;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MethodContainerConfigTest {

    private static final Method PUBLIC_METHOD = getMethod("publicMethod", Integer.class);
    private static final Method PRIVATE_METHOD = getMethod("privateMethod", Integer.class);
    private static final Method WRONG_SIGNATURE__METHOD = getMethod("wrongSignatureMethod", String.class);

    private static Method getMethod(String name, Class... args) {
        try {
            return MethodContainerConfigTest.class.getDeclaredMethod(name, args);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Invocation invocation;

    @Test
    public void A_public_method_with_the_right_signature_can_be_invoked() {

        // Given
        final int integer = 99;
        invocation = mock(Invocation.class);
        final MethodContainerConfig<Integer> config = new MethodContainerConfig<>(PUBLIC_METHOD, this);

        // When
        config.configure(integer);

        // Then
        verify(invocation).invoke(integer);
    }

    @Test(expected = IllegalStateException.class)
    public void A_private_method_with_the_right_signature_fails() {

        // Given
        final MethodContainerConfig<Integer> config = new MethodContainerConfig<>(PRIVATE_METHOD, this);

        // When
        config.configure(99);
    }

    @Test(expected = IllegalStateException.class)
    public void A_public_method_with_the_wrong_signature_fails() {

        // Given
        final MethodContainerConfig<Integer> config = new MethodContainerConfig<>(WRONG_SIGNATURE__METHOD, this);

        // When
        config.configure(99);
    }

    public void publicMethod(Integer integer) {
        invocation.invoke(integer);
    }

    private void privateMethod(Integer integer) {
        invocation.invoke(integer);
    }

    private void wrongSignatureMethod(String string) {
    }

    private static interface Invocation {
        void invoke(Integer integer);
    }
}
