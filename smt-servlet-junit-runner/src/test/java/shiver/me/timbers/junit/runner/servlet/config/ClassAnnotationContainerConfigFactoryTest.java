package shiver.me.timbers.junit.runner.servlet.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static shiver.me.timbers.junit.runner.servlet.config.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

public class ClassAnnotationContainerConfigFactoryTest {

    @Test
    public void A_class_with_no_configuration_should_produce_a_null_container_config() {

        // Given
        class TestClass {
        }

        // When
        final ContainerConfiguration<Object> actual = new ClassAnnotationContainerConfigFactory<>().create(new TestClass());

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_class_with_configuration_should_produce_the_config_in_the_annotation() {

        // Given
        @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration(TestContainerConfiguration.class)
        class TestClass {
        }

        // When
        final ContainerConfiguration<TestContainer> actual = new ClassAnnotationContainerConfigFactory<TestContainer>()
                .create(new TestClass());

        final TestContainer container = mock(TestContainer.class);

        actual.configure(container);

        // Then
        verify(container).test();
    }

    @Test
    public void An_empty_class_configuration_should_produce_the_null_config() {

        // Given
        @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration
        class TestClass {
        }

        // When
        final ContainerConfiguration<TestContainer> actual = new ClassAnnotationContainerConfigFactory<TestContainer>()
                .create(new TestClass());

        final TestContainer container = mock(TestContainer.class);

        actual.configure(container);

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
        verifyZeroInteractions(container);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_class_with_configuration_that_does_not_have_a_public_default_constructor_fails() {

        // Given
        @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration(TestContainerConfigurationWithNoDefaultConstructor.class)
        class TestClass {
        }

        // When
        new ClassAnnotationContainerConfigFactory<TestContainer>().create(new TestClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_class_with_configuration_that_cannot_be_instantiate_fails() {

        // Given
        @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfiguration(TestContainerConfigurationInterface.class)
        class TestClass {
        }

        // When
        new ClassAnnotationContainerConfigFactory<TestContainer>().create(new TestClass());
    }

    public static class TestContainerConfiguration implements ContainerConfiguration<TestContainer> {
        @Override
        public void configure(TestContainer container) {
            container.test();
        }
    }

    public static class TestContainerConfigurationWithNoDefaultConstructor implements ContainerConfiguration<TestContainer> {

        @SuppressWarnings("UnusedParameters")
        public TestContainerConfigurationWithNoDefaultConstructor(Object argument) {
        }

        @Override
        public void configure(TestContainer container) {
        }
    }

    public static interface TestContainerConfigurationInterface extends ContainerConfiguration<TestContainer> {
    }

    private static interface TestContainer {
        void test();
    }
}
