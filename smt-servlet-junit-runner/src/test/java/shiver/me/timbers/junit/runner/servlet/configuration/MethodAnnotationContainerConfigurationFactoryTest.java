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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

public class MethodAnnotationContainerConfigurationFactoryTest {

    @Test
    public void A_class_with_no_configured_method_returns_a_null_config() {

        // Given
        class TestClass {
        }

        // When
        final ContainerConfiguration<Object> actual = new MethodAnnotationContainerConfigurationFactory<>().create(new TestClass());

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_class_with_a_configured_method_returns_a_config() {

        // Given
        class TestClass {

            @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration
            public void config(TestContainer container) {
                container.test();
            }
        }

        final TestContainer container = mock(TestContainer.class);

        // When
        final ContainerConfiguration<TestContainer> actual = new MethodAnnotationContainerConfigurationFactory<TestContainer>()
                .create(new TestClass());

        actual.configure(container);

        // Then
        verify(container).test();
    }

    @Test
    public void A_class_with_a_configured_private_method_returns_a_null_config() {

        // Given
        class TestClass {

            @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration
            private void config(TestContainer container) {
                container.test();
            }
        }

        // When
        final ContainerConfiguration<Object> actual = new MethodAnnotationContainerConfigurationFactory<>().create(new TestClass());

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_class_with_a_configured_method_that_has_the_wrong_signature_fails() {

        // Given
        class TestClass {

            @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration
            public void config(Integer integer) {
            }
        }

        final TestContainer container = mock(TestContainer.class);

        // When
        final ContainerConfiguration<TestContainer> actual = new MethodAnnotationContainerConfigurationFactory<TestContainer>()
                .create(new TestClass());

        actual.configure(container);
    }

    public static interface TestContainer {
        void test();
    }
}
