package shiver.me.timbers.junit.runner.test;

import shiver.me.timbers.junit.runner.config.ContainerConfig;

import java.util.concurrent.atomic.AtomicReference;

public class TestContainerConfig implements ContainerConfig<TestServletContainer> {

    public static final AtomicReference<TestServletContainer> TEST_SERVLET_CONTAINER_REFERENCE =
            new AtomicReference<>();

    @Override
    public void configure(TestServletContainer container) {
        TEST_SERVLET_CONTAINER_REFERENCE.set(container);
    }
}
