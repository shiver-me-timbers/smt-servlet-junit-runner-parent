package shiver.me.timbers.junit.runner.test;

import org.junit.runners.model.InitializationError;
import shiver.me.timbers.junit.runner.AnnotationServletJUnitRunner;
import shiver.me.timbers.junit.runner.Container;
import shiver.me.timbers.junit.runner.Servlets;
import shiver.me.timbers.junit.runner.config.ContainerConfig;
import shiver.me.timbers.junit.runner.config.PortConfig;

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
                    public void config(ContainerConfig<TestServletContainer> containerConfig) {
                        container.config(containerConfig);
                        containerConfig.configure(container);
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
