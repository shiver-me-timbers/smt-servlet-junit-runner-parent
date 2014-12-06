package shiver.me.timbers.junit.runner.servlet.test;

import shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration;

import java.util.concurrent.atomic.AtomicReference;

public class TestContainerConfiguration implements ContainerConfiguration<TestServletContainer> {

    public static final AtomicReference<TestServletContainer> TEST_SERVLET_CONTAINER_REFERENCE =
            new AtomicReference<>();

    @Override
    public void configure(TestServletContainer container) {
        TEST_SERVLET_CONTAINER_REFERENCE.set(container);
    }
}
