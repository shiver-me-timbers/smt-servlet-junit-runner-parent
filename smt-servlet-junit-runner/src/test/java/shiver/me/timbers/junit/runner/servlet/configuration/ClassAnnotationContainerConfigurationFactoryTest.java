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
import shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.inject.AnnotationExtractor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.configuration.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

public class ClassAnnotationContainerConfigurationFactoryTest {

    @Test
    public void A_class_with_no_configuration_should_produce_a_null_container_config() {

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<ContainerConfiguration> annotationExtractor = mock(AnnotationExtractor.class);

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(null);

        // When
        final shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration<Object> actual =
                new ClassAnnotationContainerConfigurationFactory<>(annotationExtractor).create(new TestClass());

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void A_class_with_configuration_should_produce_the_config_in_the_annotation() {

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<ContainerConfiguration> annotationExtractor = mock(AnnotationExtractor.class);
        final ContainerConfiguration containerConfiguration = mock(ContainerConfiguration.class);

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(containerConfiguration);
        when(containerConfiguration.value()).thenReturn((Class) TestContainerConfiguration.class);

        // When
        final shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration<TestContainer> actual =
                new ClassAnnotationContainerConfigurationFactory<TestContainer>(annotationExtractor)
                        .create(new TestClass());

        final TestContainer container = mock(TestContainer.class);

        actual.configure(container);

        // Then
        verify(container).test();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void An_empty_class_configuration_should_produce_the_null_config() {

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<ContainerConfiguration> annotationExtractor = mock(AnnotationExtractor.class);
        final ContainerConfiguration containerConfiguration = mock(ContainerConfiguration.class);

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(containerConfiguration);
        when(containerConfiguration.value()).thenReturn((Class) NullContainerConfiguration.class);

        // When
        final shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration<TestContainer> actual =
                new ClassAnnotationContainerConfigurationFactory<TestContainer>(annotationExtractor)
                        .create(new TestClass());

        final TestContainer container = mock(TestContainer.class);

        actual.configure(container);

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
        verifyZeroInteractions(container);
    }

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("unchecked")
    public void A_class_with_configuration_that_does_not_have_a_public_default_constructor_fails() {

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<ContainerConfiguration> annotationExtractor = mock(AnnotationExtractor.class);
        final ContainerConfiguration containerConfiguration = mock(ContainerConfiguration.class);

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(containerConfiguration);
        when(containerConfiguration.value())
                .thenReturn((Class) TestContainerConfigurationWithNoDefaultConstructor.class);

        // When
        new ClassAnnotationContainerConfigurationFactory<TestContainer>(annotationExtractor).create(new TestClass());
    }

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("unchecked")
    public void A_class_with_configuration_that_cannot_be_instantiate_fails() {

        @SuppressWarnings("unchecked")
        final AnnotationExtractor<ContainerConfiguration> annotationExtractor = mock(AnnotationExtractor.class);
        final ContainerConfiguration containerConfiguration = mock(ContainerConfiguration.class);

        // Given
        when(annotationExtractor.create(TestClass.class)).thenReturn(containerConfiguration);
        when(containerConfiguration.value()).thenReturn((Class) TestContainerConfigurationInterface.class);

        // When
        new ClassAnnotationContainerConfigurationFactory<TestContainer>(annotationExtractor).create(new TestClass());
    }

    private static class TestClass {
    }

    public static class TestContainerConfiguration
            implements shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration<TestContainer> {
        @Override
        public void configure(TestContainer container) {
            container.test();
        }
    }

    public static class TestContainerConfigurationWithNoDefaultConstructor
            implements shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration<TestContainer> {

        @SuppressWarnings("UnusedParameters")
        public TestContainerConfigurationWithNoDefaultConstructor(Object argument) {
        }

        @Override
        public void configure(TestContainer container) {
        }
    }

    public static interface TestContainerConfigurationInterface
            extends shiver.me.timbers.junit.runner.servlet.configuration.ContainerConfiguration<TestContainer> {
    }

    private static interface TestContainer {
        void test();
    }
}
