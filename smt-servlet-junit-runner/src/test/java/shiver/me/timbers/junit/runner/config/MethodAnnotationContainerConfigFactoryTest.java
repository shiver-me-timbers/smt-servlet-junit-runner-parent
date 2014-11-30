package shiver.me.timbers.junit.runner.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.junit.runner.config.NullContainerConfig.NULL_CONTAINER_CONFIG;

/**
 * @author Karl Bennett
 */
public class MethodAnnotationContainerConfigFactoryTest {

    @Test
    public void A_class_with_no_configured_method_returns_a_null_config() {

        // Given
        class TestClass {
        }

        // When
        final ContainerConfig<Object> actual = new MethodAnnotationContainerConfigFactory<>().create(new TestClass());

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_class_with_a_configured_method_returns_a_config() {

        // Given
        class TestClass {

            @shiver.me.timbers.junit.runner.annotation.ContainerConfig
            public void config(TestContainer container) {
                container.test();
            }
        }

        final TestContainer container = mock(TestContainer.class);

        // When
        final ContainerConfig<TestContainer> actual = new MethodAnnotationContainerConfigFactory<TestContainer>()
                .create(new TestClass());

        actual.configure(container);

        // Then
        verify(container).test();
    }

    @Test
    public void A_class_with_a_configured_method_returns_a_null_config() {

        // Given
        class TestClass {

            @shiver.me.timbers.junit.runner.annotation.ContainerConfig
            private void config(TestContainer container) {
                container.test();
            }
        }

        // When
        final ContainerConfig<Object> actual = new MethodAnnotationContainerConfigFactory<>().create(new TestClass());

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void A_class_with_a_configured_method_that_has_the_wrong_signature_fails() {

        // Given
        class TestClass {

            @shiver.me.timbers.junit.runner.annotation.ContainerConfig
            public void config(Integer integer) {
            }
        }

        final TestContainer container = mock(TestContainer.class);

        // When
        final ContainerConfig<TestContainer> actual = new MethodAnnotationContainerConfigFactory<TestContainer>()
                .create(new TestClass());

        actual.configure(container);
    }

    public static interface TestContainer {
        void test();
    }
}