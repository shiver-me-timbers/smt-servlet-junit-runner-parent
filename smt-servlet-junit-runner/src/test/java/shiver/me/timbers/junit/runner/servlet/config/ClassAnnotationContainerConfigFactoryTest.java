package shiver.me.timbers.junit.runner.servlet.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.junit.runner.servlet.config.NullContainerConfig.NULL_CONTAINER_CONFIG;

public class ClassAnnotationContainerConfigFactoryTest {

    @Test
    public void A_class_with_no_configuration_should_produce_a_null_container_config() {

        // Given
        class TestClass {
        }

        // When
        final ContainerConfig<Object> actual = new ClassAnnotationContainerConfigFactory<>().create(new TestClass());

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_class_with_configuration_should_produce_the_config_in_the_annotation() {

        // Given
        @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfig(TestContainerConfig.class)
        class TestClass {
        }

        // When
        final ContainerConfig<TestContainer> actual = new ClassAnnotationContainerConfigFactory<TestContainer>()
                .create(new TestClass());

        final TestContainer container = mock(TestContainer.class);

        actual.configure(container);

        // Then
        verify(container).test();
    }

    @Test(expected = IllegalStateException.class)
    public void A_class_with_configuration_that_does_not_have_a_public_default_constructor_fails() {

        // Given
        @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfig(TestContainerConfigWithNoDefaultConstructor.class)
        class TestClass {
        }

        // When
        new ClassAnnotationContainerConfigFactory<TestContainer>().create(new TestClass());
    }

    @Test(expected = IllegalStateException.class)
    public void A_class_with_configuration_that_cannot_be_instantiate_fails() {

        // Given
        @shiver.me.timbers.junit.runner.servlet.annotation.ContainerConfig(TestContainerConfigInterface.class)
        class TestClass {
        }

        // When
        new ClassAnnotationContainerConfigFactory<TestContainer>().create(new TestClass());
    }

    public static class TestContainerConfig implements ContainerConfig<TestContainer> {
        @Override
        public void configure(TestContainer container) {
            container.test();
        }
    }

    public static class TestContainerConfigWithNoDefaultConstructor implements ContainerConfig<TestContainer> {

        @SuppressWarnings("UnusedParameters")
        public TestContainerConfigWithNoDefaultConstructor(Object argument) {
        }

        @Override
        public void configure(TestContainer container) {
        }
    }

    public static interface TestContainerConfigInterface extends ContainerConfig<TestContainer> {
    }

    private static interface TestContainer {
        void test();
    }
}
