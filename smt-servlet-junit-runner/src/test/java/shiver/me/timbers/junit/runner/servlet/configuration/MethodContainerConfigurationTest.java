/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.servlet.configuration;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MethodContainerConfigurationTest {

    private static final Method PUBLIC_METHOD = getMethod("publicMethod", Integer.class);
    private static final Method PRIVATE_METHOD = getMethod("privateMethod", Integer.class);
    private static final Method WRONG_SIGNATURE__METHOD = getMethod("wrongSignatureMethod", String.class);

    private static Method getMethod(String name, Class... args) {
        try {
            return MethodContainerConfigurationTest.class.getDeclaredMethod(name, args);
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
        final MethodContainerConfiguration<Integer> config = new MethodContainerConfiguration<>(PUBLIC_METHOD, this);

        // When
        config.configure(integer);

        // Then
        verify(invocation).invoke(integer);
    }

    @Test(expected = IllegalStateException.class)
    public void A_private_method_with_the_right_signature_fails() {

        // Given
        final MethodContainerConfiguration<Integer> config = new MethodContainerConfiguration<>(PRIVATE_METHOD, this);

        // When
        config.configure(99);
    }

    @Test(expected = IllegalStateException.class)
    public void A_public_method_with_the_wrong_signature_fails() {

        // Given
        final MethodContainerConfiguration<Integer> config = new MethodContainerConfiguration<>(WRONG_SIGNATURE__METHOD, this);

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
