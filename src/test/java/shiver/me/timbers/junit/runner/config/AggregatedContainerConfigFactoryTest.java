package shiver.me.timbers.junit.runner.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.junit.runner.config.NullContainerConfig.NULL_CONTAINER_CONFIG;

public class AggregatedContainerConfigFactoryTest {

    @Test
    public void A_null_container_config_is_returned_if_no_container_config_factories_supplied() {

        final ContainerConfig<Object> actual = new AggregatedContainerConfigFactory<>().create(new Object());

        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_null_container_config_is_returned_if_the_supplied_container_config_factories() {

        final ContainerConfig<Object> actual = new AggregatedContainerConfigFactory<Object>(
                mock(ContainerConfigFactory.class),
                mock(ContainerConfigFactory.class),
                mock(ContainerConfigFactory.class)
        ).create(new Object());

        assertEquals(NULL_CONTAINER_CONFIG, actual);
    }

    @Test
    public void A_container_config_is_returned_if_a_supplied_container_config_factory_returns_one() {

        final Object test = new Object();

        final ContainerConfig expected = mock(ContainerConfig.class);

        final ContainerConfigFactory configFactory = mock(ContainerConfigFactory.class);
        when(configFactory.create(test)).thenReturn(expected);

        final ContainerConfig<Object> actual = new AggregatedContainerConfigFactory<Object>(
                mock(ContainerConfigFactory.class),
                configFactory,
                mock(ContainerConfigFactory.class)
        ).create(test);

        assertEquals(expected, actual);
    }
}