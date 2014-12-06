package shiver.me.timbers.junit.runner.servlet.test;

import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.servlet.AnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.servlet.Container;
import shiver.me.timbers.junit.runner.servlet.Servlets;
import shiver.me.timbers.junit.runner.servlet.config.ContainerConfiguration;
import shiver.me.timbers.junit.runner.servlet.config.PortConfig;

public class TestAnnotationServletJUnitRunner extends AnnotationServletJUnitRunner<TestServletContainer> {

    public TestAnnotationServletJUnitRunner(Class test) throws InitializationError {
        super(
                new Container<TestServletContainer>() {

                    private final TestServletContainer container = new TestServletContainer();

                    @Override
                    public void config(PortConfig portConfig) {
                        container.config(portConfig);
                    }

                    @Override
                    public void config(ContainerConfiguration<TestServletContainer> containerConfiguration) {
                        container.config(containerConfiguration);
                        containerConfiguration.configure(container);
                    }

                    @Override
                    public void start() {
                        container.start();
                    }

                    @Override
                    public void load(Servlets servlets) {
                        container.load(servlets);
                    }

                    @Override
                    public void shutdown() {
                        container.shutdown();
                    }
                },
                test
        );
    }
}
