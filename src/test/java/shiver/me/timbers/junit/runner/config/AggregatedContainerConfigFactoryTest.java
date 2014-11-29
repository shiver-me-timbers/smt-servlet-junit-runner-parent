package shiver.me.timbers.junit.runner.config;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.config.NullContainerConfig.NULL_CONTAINER_CONFIG;

public class AggregatedContainerConfigFactoryTest {

    private static final Object TEST = new Object();

    private ContainerConfigFactory nullConfig;

    @Before
    public void setUp() {
        nullConfig = mock(ContainerConfigFactory.class);
        when(nullConfig.create(TEST)).thenReturn(NULL_CONTAINER_CONFIG);
    }

    @Test
    public void A_null_container_config_is_returned_if_no_container_config_factories_supplied() {

        // When
        final ContainerConfig<Object> actual = new AggregatedContainerConfigFactory<>().create(TEST);

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_null_container_config_is_returned_if_the_supplied_container_config_factories() {

        // When
        final ContainerConfig<Object> actual = new AggregatedContainerConfigFactory<Object>(nullConfig, nullConfig,
                nullConfig).create(TEST);

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_container_config_is_returned_if_a_supplied_container_config_factory_returns_one() {

        // Given
        final ContainerConfig expected = mock(ContainerConfig.class);

        final ContainerConfigFactory configFactory = mock(ContainerConfigFactory.class);
        when(configFactory.create(TEST)).thenReturn(expected);

        // When
        final ContainerConfig<Object> actual = new AggregatedContainerConfigFactory<Object>(nullConfig, configFactory,
                nullConfig).create(TEST);

        // Then
        assertEquals(expected, actual);
    }
}