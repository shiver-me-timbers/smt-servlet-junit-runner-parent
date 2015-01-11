package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class NullEmptyFactoryTest {

    @Test
    public void Null_empty_factory_produces_null() throws Exception {

        // When
        final Object actual = new NullEmptyFactory<>().create();

        // Then
        assertNull(actual);
    }
}
