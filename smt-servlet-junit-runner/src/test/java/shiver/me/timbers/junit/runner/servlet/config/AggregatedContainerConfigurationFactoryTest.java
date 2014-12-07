package shiver.me.timbers.junit.runner.servlet.config;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.servlet.config.NullContainerConfiguration.NULL_CONTAINER_CONFIG;

public class AggregatedContainerConfigurationFactoryTest {

    private static final Object TEST = new Object();

    private ContainerConfigurationFactory nullConfig;

    @Before
    public void setUp() {
        nullConfig = mock(ContainerConfigurationFactory.class);
        when(nullConfig.create(TEST)).thenReturn(NULL_CONTAINER_CONFIG);
    }

    @Test
    public void A_null_container_config_is_returned_if_no_container_config_factories_supplied() {

        // When
        final ContainerConfiguration<Object> actual = new AggregatedContainerConfigurationFactory<>().create(TEST);

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_null_container_config_is_returned_if_the_supplied_container_config_factories() {

        // When
        final ContainerConfiguration<Object> actual = new AggregatedContainerConfigurationFactory<Object>(nullConfig, nullConfig,
                nullConfig).create(TEST);

        // Then
        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_container_config_is_returned_if_a_supplied_container_config_factory_returns_one() {

        // Given
        final ContainerConfiguration expected = mock(ContainerConfiguration.class);

        final ContainerConfigurationFactory configFactory = mock(ContainerConfigurationFactory.class);
        when(configFactory.create(TEST)).thenReturn(expected);

        // When
        final ContainerConfiguration<Object> actual = new AggregatedContainerConfigurationFactory<Object>(nullConfig, configFactory,
                nullConfig).create(TEST);

        // Then
        assertEquals(expected, actual);
    }
}